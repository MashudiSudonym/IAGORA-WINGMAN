package com.iagora.wingman.auth.otp.presentation.event

sealed class InputPhoneNumberDataEvent {
    data class PhoneNumberFieldChange(val phoneNumber: String): InputPhoneNumberDataEvent()

    object SendRequestOTPCode : InputPhoneNumberDataEvent()
    object Submit : InputPhoneNumberDataEvent()
}
