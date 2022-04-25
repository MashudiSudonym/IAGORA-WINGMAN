package com.iagora.wingman.auth.otp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.R
import com.iagora.wingman.auth.otp.domain.use_case.count_down_timer_use_case.CountDownTimerUseCase
import com.iagora.wingman.auth.otp.domain.use_case.send_otp_use_case.SendOTPUseCase
import com.iagora.wingman.auth.otp.domain.use_case.verify_otp_use_case.VerifyOTPWithSaveCredentialsUseCase
import com.iagora.wingman.auth.otp.presentation.state.CountDownState
import com.iagora.wingman.auth.otp.presentation.state.InputOTPCodeState
import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.common.util.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthVerifyOTPCodeViewModel @Inject constructor(
    private val verifyOTPWithSaveCredentialsUseCase: VerifyOTPWithSaveCredentialsUseCase,
    private val sendOTPUseCase: SendOTPUseCase,
    private val countDownTimerUseCase: CountDownTimerUseCase
) : ViewModel() {
    private val _inputOTPCodeState = MutableStateFlow(InputOTPCodeState())
    val inputOTPCodeState: StateFlow<InputOTPCodeState> = _inputOTPCodeState.asStateFlow()

    private val _countDownState = MutableStateFlow(CountDownState())
    val countDownState: StateFlow<CountDownState> = _countDownState.asStateFlow()

    private val inputOTPCodeErrorChannel = Channel<UIText>()
    val inputOTPCodeErrors = inputOTPCodeErrorChannel.receiveAsFlow()

    var otpCodeText by mutableStateOf("")
    private var validationStatusIsSuccess by mutableStateOf(false)

    private var job: Job? = null

    fun onOTPCodeChange(otpCode: String) {
        otpCodeText = otpCode
    }

    fun onUpdatedValidationStatusChange(isSuccess: Boolean) {
        validationStatusIsSuccess = isSuccess
    }

    fun changeValidationSuccessScreenStatus() {
        viewModelScope.launch {
            _inputOTPCodeState.update { it.copy(isSuccess = validationStatusIsSuccess) }
        }
    }

    fun validationOTPCodeTextFieldAndSendOTPVerification(phoneNumber: String) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            _inputOTPCodeState.update { it.copy(isLoading = true) }

            delay(500L)

            when {
                otpCodeText.isBlank() -> {
                    _inputOTPCodeState.update { data ->
                        data.copy(
                            isLoading = false,
                            isTextFieldError = true,
                            errorMessage = UIText.StringResource(R.string.otp_field_blank)
                        )
                    }

                    reRunCountDown()
                }
                otpCodeText.length < 6 -> {
                    _inputOTPCodeState.update { data ->
                        data.copy(
                            isLoading = false,
                            isTextFieldError = true,
                            errorMessage = UIText.StringResource(R.string.error_otp_code_not_valid)
                        )
                    }

                    reRunCountDown()
                }
                otpCodeText.length > 7 -> {
                    _inputOTPCodeState.update { data ->
                        data.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = UIText.StringResource(R.string.error_otp_code_not_valid)
                        )
                    }

                    reRunCountDown()
                }
                else -> {
                    // send verification code
                    verifyOTPWithSaveCredentialsUseCase(phoneNumber, otpCodeText).cancellable()
                        .collect { result ->
                            when (result) {
                                is Resource.Error -> {
                                    _inputOTPCodeState.update { data ->
                                        data.copy(
                                            isLoading = false,
                                            isError = true,
                                            isTextFieldError = true,
                                            errorMessage = result.message ?: UIText.unknownError()
                                        )
                                    }

                                    inputOTPCodeErrorChannel.send(
                                        result.message ?: UIText.unknownError()
                                    )
                                }
                                is Resource.Loading ->
                                    _inputOTPCodeState.update { it.copy(isLoading = true) }
                                is Resource.Success -> {
                                    _inputOTPCodeState.update { data ->
                                        data.copy(
                                            isLoading = false,
                                            isError = false,
                                            isTextFieldError = false,
                                            isSuccess = true,
                                            isCompleteRegister = result.data?.isCompleteRegister
                                                ?: false,
                                            errorMessage = UIText.DynamicString("")
                                        )
                                    }
                                }
                            }
                        }
                }
            }
        }
    }

    fun requestNewOTPCode(phoneNumber: String) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            sendOTPUseCase(phoneNumber).cancellable().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _inputOTPCodeState.update { data ->
                            data.copy(
                                isLoading = false,
                                isError = true,
                            )
                        }

                        inputOTPCodeErrorChannel.send(
                            result.message ?: UIText.unknownError()
                        )

                        // run count down again
                        reRunCountDown()
                    }
                    is Resource.Loading -> {
                        _inputOTPCodeState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _inputOTPCodeState.update { it.copy(isLoading = false, isError = false) }

                        // run count down again
                        reRunCountDown()
                    }
                }
            }
        }
    }

    fun countDownTimer() {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.Main) {
            countDownTimerUseCase().cancellable().collect { data ->
                Timber.d(data.toString())

                if (data == 0) {
                    _countDownState.update { it.copy(isCountDownStop = true) }
                }
            }
        }
    }

    private fun reRunCountDown() {
        countDownTimer()
        _countDownState.update { data ->
            data.copy(isCountDownStop = false)
        }
    }
}