package com.iagora.wingman.auth.otp.data.mapper

import com.iagora.wingman.auth.otp.data.remote.dao.OTPResponseDTO
import com.iagora.wingman.auth.otp.domain.model.OTPResponse

fun OTPResponseDTO.toOTPResponse(): OTPResponse {
    return OTPResponse(
        status = status,
        message = message
    )
}

fun OTPResponse.toOTPResponseDTO(): OTPResponseDTO {
    return OTPResponseDTO(
        status = status,
        message = message
    )
}