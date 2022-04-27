package com.iagora.wingman.auth.request_token.data.mapper

import com.iagora.wingman.auth.request_token.data.remote.dto.RequestTokenResponseDTO
import com.iagora.wingman.auth.request_token.domain.model.RequestTokenResponse

fun RequestTokenResponseDTO.toRequestTokenResponse(): RequestTokenResponse {
    return RequestTokenResponse(
        status = status,
        message = message,
        result = result.toRequestTokenResult()
    )
}

fun RequestTokenResponse.toRequestTokenResponseDTO(): RequestTokenResponseDTO {
    return RequestTokenResponseDTO(
        status = status,
        message = message,
        result = result.toRequestTokenResultDTO()
    )
}