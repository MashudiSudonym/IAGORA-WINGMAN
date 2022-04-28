package com.iagora.wingman.auth.registration.domain.model

import com.google.gson.annotations.SerializedName

data class CompleteWingmanDetailResult(
    val wingmanDetail: WingmanDetail,
    val id: String,
    val type: String,
    val phone: String,
    val createdAt: String,
    val updatedAt: String
)
