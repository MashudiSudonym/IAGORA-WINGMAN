package com.iagora.wingman.auth.request_token.data.mapper

import com.iagora.wingman.auth.request_token.data.remote.dto.RequestTokenResultDTO
import com.iagora.wingman.auth.request_token.domain.model.RequestTokenResult

fun RequestTokenResultDTO.toRequestTokenResult(): RequestTokenResult {
    return RequestTokenResult(
        wingmanId = wingmanId,
        accessToken = accessToken
    )
}

fun RequestTokenResult.toRequestTokenResultDTO(): RequestTokenResultDTO {
    return RequestTokenResultDTO(
        wingmanId = wingmanId,
        accessToken = accessToken
    )
}