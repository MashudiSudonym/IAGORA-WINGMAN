package com.iagora.wingman.auth.registration.domain.use_case.field_validation_use_case

import android.util.Patterns
import com.iagora.wingman.R
import com.iagora.wingman.common.domain.model.FieldValidationResultData
import com.iagora.wingman.common.util.UIText

class EmailFieldValidationUseCase {
    fun execute(email: String): FieldValidationResultData {
        if (email.isBlank()) {
            return FieldValidationResultData(
                successful = false,
                errorMessage = UIText.StringResource(R.string.error_validation_email_is_blank)
            )
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return FieldValidationResultData(
                successful = false,
                errorMessage = UIText.StringResource(R.string.error_validation_email_format_email_wrong)
            )
        }
        return FieldValidationResultData(successful = true)
    }
}