package com.iagora.wingman.auth.otp.presentation.event

sealed class AuthRequestOTPCodeStatusEvent {
    object Success: AuthRequestOTPCodeStatusEvent()
    object Error: AuthRequestOTPCodeStatusEvent()
}
