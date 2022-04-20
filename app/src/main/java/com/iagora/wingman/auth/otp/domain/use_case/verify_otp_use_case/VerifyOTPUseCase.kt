package com.iagora.wingman.auth.otp.domain.use_case.verify_otp_use_case

import com.iagora.wingman.auth.otp.domain.model.VerifyOTPResponse
import com.iagora.wingman.auth.otp.domain.repository.OTPRepository
import com.iagora.wingman.common.util.Resource
import kotlinx.coroutines.flow.Flow

class VerifyOTPUseCase(private val otpRepository: OTPRepository) {
    suspend operator fun invoke(
        phoneNumber: String,
        otpCode: String
    ): Flow<Resource<VerifyOTPResponse>> {
        return otpRepository.verifyOTP(phoneNumber, otpCode)
    }
}