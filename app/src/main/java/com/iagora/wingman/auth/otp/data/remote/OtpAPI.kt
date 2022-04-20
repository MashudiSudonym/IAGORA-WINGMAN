package com.iagora.wingman.auth.otp.data.remote

import com.iagora.wingman.auth.otp.data.remote.dao.OTPCodePhoneNumberDTO
import com.iagora.wingman.auth.otp.data.remote.dao.OTPPhoneNumberDTO
import com.iagora.wingman.auth.otp.data.remote.dao.SendOTPResponseDTO
import com.iagora.wingman.auth.otp.data.remote.dao.VerifyOTPResponseDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface OtpAPI {
    @POST("/api/v1/wingman/send-otp-wingman")
    suspend fun requestOTP(
        @Body phoneNumber: OTPPhoneNumberDTO,
    ): SendOTPResponseDTO

    @POST("/api/v1/wingman/verify-otp")
    suspend fun verifyOTP(
        @Body otpCodePhoneNumber: OTPCodePhoneNumberDTO
    ): VerifyOTPResponseDTO
}