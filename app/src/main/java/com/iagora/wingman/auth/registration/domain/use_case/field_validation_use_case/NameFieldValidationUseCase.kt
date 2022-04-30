package com.iagora.wingman.auth.registration.domain.use_case.field_validation_use_case

import com.iagora.wingman.R
import com.iagora.wingman.auth.registration.domain.model.FieldValidationResultData
import com.iagora.wingman.common.util.UIText

class NameFieldValidationUseCase {
    fun execute(name: String): FieldValidationResultData {
        if (name.isBlank()) {
            return FieldValidationResultData(
                successful = false,
                errorMessage = UIText.StringResource(R.string.error_validation_name_is_blank)
            )
        }
        return FieldValidationResultData(successful = true)
    }
}