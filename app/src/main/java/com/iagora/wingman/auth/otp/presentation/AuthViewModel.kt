package com.iagora.wingman.auth.otp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iagora.wingman.R
import com.iagora.wingman.auth.otp.domain.use_case.send_otp_use_case.SendOTPUseCase
import com.iagora.wingman.auth.otp.domain.use_case.verify_otp_use_case.VerifyOTPWithSaveCredentialsUseCase
import com.iagora.wingman.auth.otp.presentation.state.InputPhoneNumberState
import com.iagora.wingman.common.util.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val sendOTPUseCase: SendOTPUseCase,
    private val verifyOTPWithSaveCredentialsUseCase: VerifyOTPWithSaveCredentialsUseCase
) : ViewModel() {
    private val _inputPhoneNumberState = MutableStateFlow(InputPhoneNumberState())
    val inputPhoneNumberState: StateFlow<InputPhoneNumberState> =
        _inputPhoneNumberState.asStateFlow()

    private val inputPhoneNumberErrorChannel = Channel<UIText>()
    val inputPhoneNumberErrors = inputPhoneNumberErrorChannel.receiveAsFlow()

    var phoneNumberText by mutableStateOf("")
    var validationStatus by mutableStateOf(false)

    fun onUpdatedValidationStatusChange(isSuccess: Boolean) {
        validationStatus = isSuccess
    }

    fun onPhoneNumberChange(phoneNumber: String) {
        phoneNumberText = phoneNumber
    }

    fun changeValidationSuccessScreenStatus() {
        viewModelScope.launch {
            _inputPhoneNumberState.update { it.copy(isSuccess = validationStatus) }
        }
    }

    fun validationPhoneNumberTextField() {
        viewModelScope.launch {
            val regex = "^628[1-9][0-9]{6,9}$".toRegex()

            when {
                phoneNumberText.isBlank() -> {
                    _inputPhoneNumberState.update { data ->
                        data.copy(
                            isError = true,
                            errorMessage = UIText.StringResource(R.string.phone_number_field_blank)
                        )
                    }
                }
                !regex.matches(phoneNumberText) -> {
                    _inputPhoneNumberState.update { data ->
                        data.copy(
                            isError = true,
                            errorMessage = UIText.StringResource(R.string.error_phone_number_format)
                        )
                    }
                }
                else -> _inputPhoneNumberState.update { data ->
                    data.copy(
                        isSuccess = true,
                    )
                }
            }
        }
    }
}