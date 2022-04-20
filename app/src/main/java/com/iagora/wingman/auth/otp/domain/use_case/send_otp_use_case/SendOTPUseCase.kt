package com.iagora.wingman.auth.otp.domain.use_case.send_otp_use_case

import com.iagora.wingman.auth.otp.domain.model.SendOTPResponse
import com.iagora.wingman.auth.otp.domain.repository.OTPRepository
import com.iagora.wingman.common.util.Resource
import kotlinx.coroutines.flow.Flow

class SendOTPUseCase(
    private val otpRepository: OTPRepository
) {
    suspend operator fun invoke(phoneNumber: String): Flow<Resource<SendOTPResponse>> {
        return otpRepository.sendOTP(phoneNumber)
    }
}