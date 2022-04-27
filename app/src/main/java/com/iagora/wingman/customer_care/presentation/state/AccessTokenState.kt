package com.iagora.wingman.customer_care.presentation.state

data class AccessTokenState(
    val accessToken: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
