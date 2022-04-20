package com.iagora.wingman.auth.otp.data.mapper

import com.iagora.wingman.auth.otp.data.remote.dao.OTPPhoneNumberDTO
import com.iagora.wingman.auth.otp.domain.model.OTPPhoneNumber

fun OTPPhoneNumberDTO.toOTPPhoneNumber(): OTPPhoneNumber {
    return OTPPhoneNumber(
        phoneNumber = phoneNumber
    )
}

fun OTPPhoneNumber.toOTPPhoneNumberDTO(): OTPPhoneNumberDTO {
    return OTPPhoneNumberDTO(
        phoneNumber = phoneNumber
    )
}