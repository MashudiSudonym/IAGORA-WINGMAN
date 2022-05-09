package com.iagora.wingman.auth.registration.domain.use_case.field_validation_use_case

import com.iagora.wingman.R
import com.iagora.wingman.common.domain.model.FieldValidationResultData
import com.iagora.wingman.common.util.UIText

class BankAccountNumberFieldValidationUseCase {
    fun execute(bankAccountNumber: String): FieldValidationResultData {
        if (bankAccountNumber.isBlank()) {
            return FieldValidationResultData(
                successful = false,
                errorMessage = UIText.StringResource(R.string.error_validation_bank_account_number_is_blank)
            )
        }
        return FieldValidationResultData(successful = true)
    }
}