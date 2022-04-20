package com.iagora.wingman.auth.otp.data.mapper

import com.iagora.wingman.auth.otp.data.remote.dao.SendOTPResponseDTO
import com.iagora.wingman.auth.otp.domain.model.SendOTPResponse

fun SendOTPResponseDTO.toOTPResponse(): SendOTPResponse {
    return SendOTPResponse(
        status = status,
        message = message
    )
}

fun SendOTPResponse.toOTPResponseDTO(): SendOTPResponseDTO {
    return SendOTPResponseDTO(
        status = status,
        message = message
    )
}