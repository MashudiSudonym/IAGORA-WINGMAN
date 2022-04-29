package com.iagora.wingman.user_profile.presentation.event

sealed class UserProfileEvent {
    object Logout: UserProfileEvent()
}