package com.iagora.wingman.auth.otp.domain.model

data class OTPResponse(
    val status: Int,
    val code: String,
    val message: String,
)
