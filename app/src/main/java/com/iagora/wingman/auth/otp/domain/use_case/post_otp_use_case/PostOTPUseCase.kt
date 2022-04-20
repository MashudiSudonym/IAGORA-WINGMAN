package com.iagora.wingman.auth.otp.domain.use_case.post_otp_use_case

import com.iagora.wingman.auth.otp.domain.model.OTPResponse
import com.iagora.wingman.auth.otp.domain.repository.OTPRepository
import com.iagora.wingman.common.util.Resource
import kotlinx.coroutines.flow.Flow

class PostOTPUseCase(
    private val otpRepository: OTPRepository
) {
    suspend operator fun invoke(phoneNumber: String): Flow<Resource<OTPResponse>> {
        return otpRepository.postOTP(phoneNumber)
    }
}