package com.iagora.wingman.auth.otp.data.mapper

import com.iagora.wingman.auth.otp.data.remote.dao.OTPCodePhoneNumberDataDTO
import com.iagora.wingman.auth.otp.domain.model.OTPCodePhoneNumberData

fun OTPCodePhoneNumberDataDTO.toOTPCodePhoneNumberData(): OTPCodePhoneNumberData {
    return OTPCodePhoneNumberData(
        phoneNumber = phoneNumber,
        otpCode = otpCode
    )
}

fun OTPCodePhoneNumberData.toOTPCodePhoneNumberDataDTO(): OTPCodePhoneNumberDataDTO {
    return OTPCodePhoneNumberDataDTO(
        phoneNumber = phoneNumber,
        otpCode = otpCode
    )
}