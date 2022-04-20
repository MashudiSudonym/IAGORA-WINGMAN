package com.iagora.wingman.auth.otp.domain.model

import com.google.gson.annotations.SerializedName

data class OTPCodePhoneNumber(
    val phoneNumber: String,
    val otpCode: String
)
