package com.iagora.wingman.auth.otp.presentation.state

data class InputPhoneNumberState(
    val isLoading: Boolean = false,
    val textFieldHasValue: Boolean = false,

    // validation
    val errorId: Int = 0
)
