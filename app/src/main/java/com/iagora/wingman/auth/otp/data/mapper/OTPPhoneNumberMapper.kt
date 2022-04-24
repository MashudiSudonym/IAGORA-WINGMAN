package com.iagora.wingman.auth.otp.data.mapper

import com.iagora.wingman.auth.otp.data.remote.dao.OTPPhoneNumberDataDTO
import com.iagora.wingman.auth.otp.domain.model.OTPPhoneNumberData

fun OTPPhoneNumberDataDTO.toOTPPhoneNumberData(): OTPPhoneNumberData {
    return OTPPhoneNumberData(
        phoneNumber = phoneNumber
    )
}

fun OTPPhoneNumberData.toOTPPhoneNumberDataDTO(): OTPPhoneNumberDataDTO {
    return OTPPhoneNumberDataDTO(
        phoneNumber = phoneNumber
    )
}