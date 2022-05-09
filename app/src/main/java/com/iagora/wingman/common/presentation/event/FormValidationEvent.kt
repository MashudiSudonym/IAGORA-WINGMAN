package com.iagora.wingman.common.presentation.event

sealed class FormValidationEvent {
    object Success: FormValidationEvent()
}
