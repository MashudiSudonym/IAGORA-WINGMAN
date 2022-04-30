package com.iagora.wingman.auth.registration.presentation.event

sealed class ValidationEvent {
    object Success: ValidationEvent()
}
