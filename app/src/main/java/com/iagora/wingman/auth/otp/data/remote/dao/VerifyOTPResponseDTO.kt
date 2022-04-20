package com.iagora.wingman.auth.otp.data.remote.dao

import com.google.gson.annotations.SerializedName

data class VerifyOTPResponseDTO(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: VerifyOTPResultDTO
)
