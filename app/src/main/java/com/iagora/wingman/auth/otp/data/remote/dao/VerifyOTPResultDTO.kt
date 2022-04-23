package com.iagora.wingman.auth.otp.data.remote.dao

import com.google.gson.annotations.SerializedName

data class VerifyOTPResultDTO(
    @SerializedName("wingmanId")
    val wingmanId: String,
    @SerializedName("isComplateRegister")
    val isCompleteRegister: Boolean,
    @SerializedName("refreshToken")
    val refreshToken: String,
)
