package com.iagora.wingman.auth.otp.domain.repository

import com.iagora.wingman.auth.otp.domain.model.SendOTPResponse
import com.iagora.wingman.common.util.Resource
import kotlinx.coroutines.flow.Flow

interface OTPRepository {
    suspend fun sendOTP(phoneNumber: String): Flow<Resource<SendOTPResponse>>
//    suspend fun verifyOTP(phoneNumber: String, otpCode: String)
}