package com.iagora.wingman.auth.registration.presentation.event

import android.net.Uri


sealed class WingmanDocumentDataEvent {
    data class BankNameFieldChange(val bankName: String) : WingmanDocumentDataEvent()
    data class BankAccountUserNameFieldChange(val bankAccountUserName: String) :
        WingmanDocumentDataEvent()

    data class BankAccountNumberFieldChange(val bankAccountNumber: String) :
        WingmanDocumentDataEvent()

    data class UserIdCardImage(val userIdCardImage: Uri) : WingmanDocumentDataEvent()
    data class UserPoliceAgreementLetterImage(val userPoliceAgreementLetterImage: Uri) :
        WingmanDocumentDataEvent()

    object Submit : WingmanDocumentDataEvent()
}