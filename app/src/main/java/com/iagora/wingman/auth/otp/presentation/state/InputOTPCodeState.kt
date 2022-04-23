package com.iagora.wingman.auth.otp.presentation.state

data class InputOTPCodeState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val isTextFieldError: Boolean = false,
)
