package com.iagora.wingman.auth.otp.domain.repository

import com.iagora.wingman.auth.otp.domain.model.OTPResponse
import com.iagora.wingman.common.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface OTPRepository {
    suspend fun postOTP(phoneNumber: String): Flow<Resource<OTPResponse>>
}