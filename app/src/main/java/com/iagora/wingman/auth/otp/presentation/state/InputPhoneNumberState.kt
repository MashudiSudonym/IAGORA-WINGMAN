package com.iagora.wingman.auth.otp.presentation.state

import com.iagora.wingman.common.util.UIText

data class InputPhoneNumberState(
    val isLoading: Boolean = false,
    val errorMessage: UIText? = null,
    val phoneNumber: String = "",
    val phoneNumberFieldError: UIText? = null
)
