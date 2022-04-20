package com.iagora.wingman.auth.otp.data.remote.dao

import com.google.gson.annotations.SerializedName

data class OTPCodePhoneNumberDTO(
    @SerializedName("no_hp")
    val phoneNumber: String,
    @SerializedName("otp_code")
    val otpCode: String
)
