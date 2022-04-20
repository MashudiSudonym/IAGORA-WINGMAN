package com.iagora.wingman.auth.otp.domain.use_case.post_otp_use_case

import com.iagora.wingman.auth.otp.domain.repository.OTPRepository
import com.iagora.wingman.auth.ssid.domain.repository.WingmanSSIDRepository

class PostOTPUseCase(
    private val wingmanSSIDRepository: WingmanSSIDRepository,
    private val otpRepository: OTPRepository
) {
    suspend operator fun invoke(phoneNumber: String): Unit {
        return wingmanSSIDRepository.getWingmanSSID().collect {
            otpRepository.postOTP(it.data.toString(), phoneNumber)
        }
    }
}