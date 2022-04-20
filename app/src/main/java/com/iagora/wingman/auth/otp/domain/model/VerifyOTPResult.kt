package com.iagora.wingman.auth.otp.domain.model

data class VerifyOTPResult(
    val wingmanId: String,
    val refreshToken: String,
)
