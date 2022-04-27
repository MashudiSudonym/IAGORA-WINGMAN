package com.iagora.wingman.auth.request_token.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RequestTokenResponseDTO(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: RequestTokenResultDTO
)
