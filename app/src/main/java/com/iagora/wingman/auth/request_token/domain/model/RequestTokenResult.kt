package com.iagora.wingman.auth.request_token.domain.model

data class RequestTokenResult(
    val wingmanId: String,
    val accessToken: String,
)
