package com.iagora.wingman.auth.otp.data.remote.dao

import com.google.gson.annotations.SerializedName

data class OTPResponseDTO(
    @SerializedName("status")
    val status: Int,
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String,
)
