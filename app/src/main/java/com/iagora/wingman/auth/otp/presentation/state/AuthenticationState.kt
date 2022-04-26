package com.iagora.wingman.auth.otp.presentation.state

data class AuthenticationState(
    val isLoading: Boolean = true,
    val isAuthenticated: Boolean = true,
    val isError: Boolean = false
)
