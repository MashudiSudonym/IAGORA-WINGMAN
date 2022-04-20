package com.iagora.wingman.auth.otp.domain.model

data class VerifyOTPResponse(
    val status: Int,
    val message: String,
    val result: VerifyOTPResult
)
