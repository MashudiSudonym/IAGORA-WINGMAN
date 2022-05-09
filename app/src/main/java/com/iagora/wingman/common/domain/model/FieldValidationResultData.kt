package com.iagora.wingman.common.domain.model

import com.iagora.wingman.common.util.UIText

data class FieldValidationResultData(
    val successful: Boolean,
    val errorMessage: UIText? = null
)
