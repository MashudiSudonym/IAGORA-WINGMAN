package com.iagora.wingman.auth.otp.presentation.event

import com.iagora.wingman.common.util.UIText

sealed class AuthRequestOTPCodeMessageEvent {
    data class IsLoadingChange(val isLoading: Boolean): AuthRequestOTPCodeMessageEvent()
    data class ErrorMessageChange(val errorMessage: UIText): AuthRequestOTPCodeMessageEvent()
}
