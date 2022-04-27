package com.iagora.wingman.auth.request_token.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RequestTokenResultDTO(
    @SerializedName("wingmanId")
    val wingmanId: String,
    @SerializedName("accessToken")
    val accessToken: String,
)
