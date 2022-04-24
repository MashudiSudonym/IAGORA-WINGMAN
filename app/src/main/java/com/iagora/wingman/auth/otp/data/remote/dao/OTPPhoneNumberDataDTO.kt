package com.iagora.wingman.auth.otp.data.remote.dao

import com.google.gson.annotations.SerializedName
import com.iagora.wingman.common.util.Constants

data class OTPPhoneNumberDataDTO(
    @SerializedName("no_hp")
    val phoneNumber: String
)
