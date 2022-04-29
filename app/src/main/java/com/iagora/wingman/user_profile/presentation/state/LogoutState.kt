package com.iagora.wingman.user_profile.presentation.state

data class LogoutState(
    val isLoading: Boolean = false,
    val logoutSuccess: Boolean = false,
    val isError: Boolean = false
)
