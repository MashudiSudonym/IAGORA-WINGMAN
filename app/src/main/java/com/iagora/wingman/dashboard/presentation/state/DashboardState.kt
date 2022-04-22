package com.iagora.wingman.dashboard.presentation.state

data class DashboardState(
    val isLoading: Boolean = true,
    val isAuthenticated: Boolean = true,
    val isError: Boolean = false
)
