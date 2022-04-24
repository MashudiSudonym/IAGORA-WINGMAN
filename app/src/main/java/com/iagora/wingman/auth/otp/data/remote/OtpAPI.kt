package com.iagora.wingman.auth.otp.data.remote

import com.iagora.wingman.auth.otp.data.remote.dao.OTPCodePhoneNumberDataDTO
import com.iagora.wingman.auth.otp.data.remote.dao.OTPPhoneNumberDataDTO
import com.iagora.wingman.auth.otp.data.remote.dao.SendOTPResponseDTO
import com.iagora.wingman.auth.otp.data.remote.dao.VerifyOTPResponseDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface OtpAPI {
    @POST("/api/v1/wingman/send-otp-wingman")
    suspend fun requestOTP(
        @Body phoneNumberData: OTPPhoneNumberDataDTO,
    ): SendOTPResponseDTO

    @POST("/api/v1/wingman/verify-otp")
    suspend fun verifyOTP(
        @Body otpCodePhoneNumber: OTPCodePhoneNumberDataDTO
    ): VerifyOTPResponseDTO
}