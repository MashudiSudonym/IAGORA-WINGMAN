package com.iagora.wingman.auth.registration.presentation.state

import android.net.Uri
import com.iagora.wingman.common.util.UIText

data class RegistrationWingmanDocumentDataState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val bankName: String = "",
    val bankNameFieldError: UIText? = null,
    val bankAccountUserName: String = "",
    val bankAccountUserNameFieldError: UIText? = null,
    val bankAccountNumber: String = "",
    val bankAccountNumberFieldError: UIText? = null,
    val userIdCardImage: Uri = Uri.parse("file://dev/null"),
    val userIdCardImageFieldError: UIText? = null,
    val userPoliceAgreementLetterImage: Uri = Uri.parse("file://dev/null"),
    val userPoliceAgreementLetterImageFieldError: UIText? = null
)