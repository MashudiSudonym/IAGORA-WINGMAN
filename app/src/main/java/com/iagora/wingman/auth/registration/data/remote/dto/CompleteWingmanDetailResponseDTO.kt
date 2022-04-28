package com.iagora.wingman.auth.registration.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CompleteWingmanDetailResponseDTO(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: CompleteWingmanDetailResultDTO
)
