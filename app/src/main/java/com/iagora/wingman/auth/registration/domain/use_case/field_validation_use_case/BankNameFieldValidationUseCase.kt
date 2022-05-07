package com.iagora.wingman.auth.registration.domain.use_case.field_validation_use_case

import com.iagora.wingman.R
import com.iagora.wingman.auth.registration.domain.model.FieldValidationResultData
import com.iagora.wingman.common.util.UIText

class BankNameFieldValidationUseCase {
    fun execute(bankName: String): FieldValidationResultData {
        if (bankName.isBlank()) {
            return FieldValidationResultData(
                successful = false,
                errorMessage = UIText.StringResource(R.string.error_validation_bank_name_is_blank)
            )
        }
        return FieldValidationResultData(successful = true)
    }
}