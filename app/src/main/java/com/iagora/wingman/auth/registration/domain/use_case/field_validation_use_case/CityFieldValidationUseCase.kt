package com.iagora.wingman.auth.registration.domain.use_case.field_validation_use_case

import com.iagora.wingman.R
import com.iagora.wingman.auth.registration.domain.model.FieldValidationResultData
import com.iagora.wingman.common.util.UIText

class CityFieldValidationUseCase {
    fun execute(city: String): FieldValidationResultData {
        if (city.isBlank()) {
            return FieldValidationResultData(
                successful = false,
                errorMessage = UIText.StringResource(R.string.error_validation_city_is_blank)
            )
        }
        return FieldValidationResultData(successful = true)
    }
}