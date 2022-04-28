package com.iagora.wingman.auth.registration.domain.model

data class CompleteWingmanDetailResponse(
    val status: Int,
    val message: String,
    val result: CompleteWingmanDetailResult
)
