package com.iagora.wingman.auth.registration.presentation.event

sealed class WingmanDetailDataEvent {
    data class NameFieldChange(val name: String) : WingmanDetailDataEvent()
    data class EmailFieldChange(val email: String) : WingmanDetailDataEvent()
    data class AddressFieldChange(val address: String) : WingmanDetailDataEvent()
    data class CityFieldChange(val city: String) : WingmanDetailDataEvent()
    object Submit : WingmanDetailDataEvent()
}
