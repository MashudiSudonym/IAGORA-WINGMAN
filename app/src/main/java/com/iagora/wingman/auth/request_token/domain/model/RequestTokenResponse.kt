package com.iagora.wingman.auth.request_token.domain.model

data class RequestTokenResponse(
    val status: Int,
    val message: String,
    val result: RequestTokenResult
)
