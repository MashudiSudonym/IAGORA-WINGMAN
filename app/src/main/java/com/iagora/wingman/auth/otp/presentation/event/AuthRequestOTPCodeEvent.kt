package com.iagora.wingman.auth.otp.presentation.event

sealed class AuthRequestOTPCodeEvent {
    object Success: AuthRequestOTPCodeEvent()
    object Error: AuthRequestOTPCodeEvent()
}
