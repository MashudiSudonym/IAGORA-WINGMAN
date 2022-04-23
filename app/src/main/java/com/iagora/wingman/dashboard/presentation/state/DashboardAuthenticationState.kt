package com.iagora.wingman.dashboard.presentation.state

data class DashboardAuthenticationState(
    val isLoading: Boolean = true,
    val isAuthenticated: Boolean = true,
    val isError: Boolean = false
)
