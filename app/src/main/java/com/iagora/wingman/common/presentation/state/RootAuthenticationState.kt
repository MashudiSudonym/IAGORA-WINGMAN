package com.iagora.wingman.common.presentation.state

data class RootAuthenticationState(
    val isLoading: Boolean = true,
    val isAuthenticated: Boolean = true,
    val isError: Boolean = false
)
