package com.iagora.wingman.auth.request_token.domain.repository

import com.iagora.wingman.auth.request_token.domain.model.RequestTokenResponse
import com.iagora.wingman.common.util.Resource
import kotlinx.coroutines.flow.Flow

interface RequestTokenRepository {
    suspend fun RequestToken(token: String): Flow<Resource<RequestTokenResponse>>
}