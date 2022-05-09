package com.iagora.wingman.auth.otp.domain.use_case.field_validation_use_case

import com.iagora.wingman.R
import com.iagora.wingman.common.domain.model.FieldValidationResultData
import com.iagora.wingman.common.util.UIText
import timber.log.Timber

class PhoneNumberFieldValidationUseCase {
    fun execute(phoneNumber: String): FieldValidationResultData {
        val regex = "^628[1-9][0-9]{6,9}$".toRegex()

        if (phoneNumber.isBlank()) {
            return FieldValidationResultData(
                successful = false,
                errorMessage = UIText.StringResource(R.string.error_phone_number_field_blank)
            )
        }

        if (phoneNumber.length <= 9) {
            return FieldValidationResultData(
                successful = false,
                errorMessage = UIText.StringResource(R.string.error_phone_number_less_9_char)
            )
        }

        if (phoneNumber.length > 14) {
            return FieldValidationResultData(
                successful = false,
                errorMessage = UIText.StringResource(R.string.error_phone_number_more_13_char)
            )
        }

        if (!regex.matches(phoneNumber)) {
            return FieldValidationResultData(
                successful = false,
                errorMessage = UIText.StringResource(R.string.error_phone_number_format)
            )
        }

        return FieldValidationResultData(successful = true)
    }
}