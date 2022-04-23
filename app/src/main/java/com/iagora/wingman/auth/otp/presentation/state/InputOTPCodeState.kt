package com.iagora.wingman.auth.otp.presentation.state

import com.iagora.wingman.common.util.UIText

data class InputOTPCodeState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val isTextFieldError: Boolean = false,
    val errorMessage: UIText = UIText.DynamicString(""),
    val isCompleteRegister: Boolean = false,
)
