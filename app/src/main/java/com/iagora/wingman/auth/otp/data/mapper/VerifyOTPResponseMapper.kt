package com.iagora.wingman.auth.otp.data.mapper

import com.iagora.wingman.auth.otp.data.remote.dao.VerifyOTPResponseDTO
import com.iagora.wingman.auth.otp.domain.model.VerifyOTPResponse

fun VerifyOTPResponseDTO.toVerifyOTPResponse(): VerifyOTPResponse {
    return VerifyOTPResponse(
        status = status,
        message = message,
        result = result.toVerifyOTPResult()
    )
}

fun VerifyOTPResponse.toVerifyOTPResponseDTO(): VerifyOTPResponseDTO {
    return VerifyOTPResponseDTO(
        status = status,
        message = message,
        result = result.toVerifyOTPResultDTO()
    )
}