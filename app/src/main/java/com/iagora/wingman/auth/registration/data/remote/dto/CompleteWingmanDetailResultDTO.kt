package com.iagora.wingman.auth.registration.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CompleteWingmanDetailResultDTO(
    @SerializedName("wingmanDetail")
    val wingmanDetail: WingmanDetailDTO,
    @SerializedName("_id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("no_hp")
    val phone: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)
