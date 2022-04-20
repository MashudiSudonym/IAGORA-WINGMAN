package com.iagora.wingman.auth.otp.data.remote.dao

import com.google.gson.annotations.SerializedName

data class SendOTPResponseDTO(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String
)
