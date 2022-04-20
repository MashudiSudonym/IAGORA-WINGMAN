package com.iagora.wingman.auth.otp.data.remote

import com.iagora.wingman.auth.otp.data.remote.dao.OTPPhoneNumberDTO
import com.iagora.wingman.auth.otp.data.remote.dao.OTPResponseDTO
import com.iagora.wingman.common.util.Constants
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OtpAPI {
    @POST("/api/v1/wingman/send-otp-wingman")
    suspend fun requestOTP(
        @Body phoneNumber: OTPPhoneNumberDTO,
    ): OTPResponseDTO
}