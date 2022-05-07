package com.iagora.wingman.auth.registration.domain.use_case.field_validation_use_case

import android.net.Uri
import com.iagora.wingman.R
import com.iagora.wingman.auth.registration.domain.model.FieldValidationResultData
import com.iagora.wingman.common.util.UIText

class UserIdCardImageValidationUseCase {
    fun execute(userIdCardImage: Uri): FieldValidationResultData {
        val emptyImageUri = Uri.parse("file://dev/null")

        if (userIdCardImage == emptyImageUri) {
            return FieldValidationResultData(
                successful = false,
                errorMessage = UIText.StringResource(R.string.error_validation_user_id_card_is_blank)
            )
        }
        return FieldValidationResultData(successful = true)
    }
}