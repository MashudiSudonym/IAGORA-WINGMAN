package com.iagora.wingman.auth.registration.presentation.state

import com.iagora.wingman.common.util.UIText

data class RegistrationWingmanDetailDataState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val name: String = "",
    val nameFieldError: UIText? = null,
    val email: String = "",
    val emailFieldError: UIText? = null,
    val address: String = "",
    val addressFieldError: UIText? = null,
    val city: String = "",
    val cityFieldError: UIText? = null,
    val successSubmitData: Boolean = false,
)
