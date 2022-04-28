package com.iagora.wingman.auth.registration.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WingmanDetailDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String,
)
