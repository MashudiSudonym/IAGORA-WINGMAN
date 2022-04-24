package com.iagora.wingman.auth.otp.domain.model

import com.google.gson.annotations.SerializedName

data class OTPCodePhoneNumberData(
    val phoneNumber: String,
    val otpCode: String
)
