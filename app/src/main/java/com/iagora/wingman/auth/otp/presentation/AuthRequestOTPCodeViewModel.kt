package com.iagora.wingman.auth.otp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.R
import com.iagora.wingman.auth.otp.domain.use_case.is_aunthenticated_use_case.IsAuthenticatedUseCase
import com.iagora.wingman.auth.otp.domain.use_case.is_wingman_complete_data_use_case.IsWingmanCompleteDataUseCase
import com.iagora.wingman.auth.otp.domain.use_case.send_otp_use_case.SendOTPUseCase
import com.iagora.wingman.auth.otp.presentation.state.AuthenticationState
import com.iagora.wingman.auth.otp.presentation.state.InputPhoneNumberState
import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.common.util.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthRequestOTPCodeViewModel @Inject constructor(
    private val sendOTPUseCase: SendOTPUseCase,
    private val isAuthenticatedUseCase: IsAuthenticatedUseCase,
    private val isWingmanCompleteDataUseCase: IsWingmanCompleteDataUseCase
) : ViewModel() {
    private val _authenticationState = MutableStateFlow(AuthenticationState())
    val authenticationState: StateFlow<AuthenticationState> =
        _authenticationState.asStateFlow()

    private val _isWingmanCompleteDataState = MutableStateFlow(false)
    val isWingmanCompleteDataState: StateFlow<Boolean> = _isWingmanCompleteDataState.asStateFlow()

    private val _inputPhoneNumberState = MutableStateFlow(InputPhoneNumberState())
    val inputPhoneNumberState: StateFlow<InputPhoneNumberState> =
        _inputPhoneNumberState.asStateFlow()

    private val inputPhoneNumberErrorChannel = Channel<UIText>()
    val inputPhoneNumberErrors = inputPhoneNumberErrorChannel.receiveAsFlow()

    var phoneNumberText by mutableStateOf("")
    private var validationStatusIsSuccess by mutableStateOf(false)

    init {
        isAuthenticated()
        isWingmanCompleteData()
    }

    private fun isWingmanCompleteData() {
        viewModelScope.launch {
            _isWingmanCompleteDataState.value = isWingmanCompleteDataState.value
        }
    }

    private fun isAuthenticated() {
        viewModelScope.launch {
            isAuthenticatedUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> _authenticationState.update { it.copy(isError = true) }
                    is Resource.Loading -> _authenticationState.update { it.copy(isLoading = result.isLoading) }
                    is Resource.Success -> {
                        result.data.let { data ->
                            _authenticationState.update {
                                it.copy(
                                    isAuthenticated = data?.isAuthenticatedStatus ?: false
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun onUpdatedValidationStatusChange(isSuccess: Boolean) {
        validationStatusIsSuccess = isSuccess
    }

    fun onPhoneNumberChange(phoneNumber: String) {
        phoneNumberText = phoneNumber
    }

    fun changeValidationSuccessScreenStatus() {
        viewModelScope.launch {
            _inputPhoneNumberState.update { it.copy(isSuccess = validationStatusIsSuccess) }
        }
    }

    private var job: Job? = null

    fun validationPhoneNumberTextFieldAndSendOTPRequest() {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            val regex = "^628[1-9][0-9]{6,9}$".toRegex()

            _inputPhoneNumberState.update { it.copy(isLoading = true) }

            delay(500L)

            when {
                phoneNumberText.isBlank() -> {
                    _inputPhoneNumberState.update { data ->
                        data.copy(
                            isLoading = false,
                            isTextFieldError = true,
                            errorMessage = UIText.StringResource(R.string.phone_number_field_blank)
                        )
                    }
                }
                phoneNumberText.length <= 9 -> {
                    _inputPhoneNumberState.update { data ->
                        data.copy(
                            isLoading = false,
                            isTextFieldError = true,
                            errorMessage = UIText.StringResource(R.string.error_phone_number_less_9_char)
                        )
                    }
                }
                phoneNumberText.length > 14 -> {
                    _inputPhoneNumberState.update { data ->
                        data.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = UIText.StringResource(R.string.error_phone_number_more_13_char)
                        )
                    }
                }
                !regex.matches(phoneNumberText) -> {
                    _inputPhoneNumberState.update { data ->
                        data.copy(
                            isLoading = false,
                            isTextFieldError = true,
                            errorMessage = UIText.StringResource(R.string.error_phone_number_format)
                        )
                    }
                }
                else -> {
                    // request otp code from server
                    sendOTPUseCase(phoneNumberText).cancellable().collect { result ->
                        when (result) {
                            is Resource.Error -> {
                                _inputPhoneNumberState.update { data ->
                                    data.copy(
                                        isLoading = false,
                                        isError = true,
                                        isTextFieldError = false
                                    )
                                }
                                inputPhoneNumberErrorChannel.send(
                                    result.message ?: UIText.unknownError()
                                )
                            }
                            is Resource.Loading -> _inputPhoneNumberState.update { it.copy(isLoading = true) }
                            is Resource.Success -> _inputPhoneNumberState.update { data ->
                                data.copy(
                                    isLoading = false,
                                    isError = false,
                                    isTextFieldError = false,
                                    isSuccess = true,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}