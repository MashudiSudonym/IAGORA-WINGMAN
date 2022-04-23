package com.iagora.wingman.auth.otp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.auth.otp.domain.use_case.count_down_timer_use_case.CountDownTimerUseCase
import com.iagora.wingman.auth.otp.domain.use_case.verify_otp_use_case.VerifyOTPWithSaveCredentialsUseCase
import com.iagora.wingman.auth.otp.presentation.state.CountDownState
import com.iagora.wingman.auth.otp.presentation.state.InputOTPCodeState
import com.iagora.wingman.common.util.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthVerifyOTPCodeViewModel @Inject constructor(
    private val verifyOTPWithSaveCredentialsUseCase: VerifyOTPWithSaveCredentialsUseCase,
    private val countDownTimerUseCase: CountDownTimerUseCase
) : ViewModel() {
    private val _inputOTPCodeState = MutableStateFlow(InputOTPCodeState())
    val inputOTPCodeState: StateFlow<InputOTPCodeState> = _inputOTPCodeState.asStateFlow()

    private val _countDownState = MutableStateFlow(CountDownState())
    val countDownState: StateFlow<CountDownState> = _countDownState.asStateFlow()

    private val inputOTPCodeErrorChannel = Channel<UIText>()
    val inputOTPCodeErrors = inputOTPCodeErrorChannel.receiveAsFlow()

    private var job: Job? = null

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
}