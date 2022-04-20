package com.iagora.wingman.auth.otp.data.mapper

import com.iagora.wingman.auth.otp.data.remote.dao.OTPCodePhoneNumberDTO
import com.iagora.wingman.auth.otp.domain.model.OTPCodePhoneNumber

fun OTPCodePhoneNumberDTO.toOTPCodePhoneNumber(): OTPCodePhoneNumber {
    return OTPCodePhoneNumber(
        phoneNumber = phoneNumber,
        otpCode = otpCode
    )
}

fun OTPCodePhoneNumber.toOTPCodePhoneNumberDTO(): OTPCodePhoneNumberDTO {
    return OTPCodePhoneNumberDTO(
        phoneNumber = phoneNumber,
        otpCode = otpCode
    )
}