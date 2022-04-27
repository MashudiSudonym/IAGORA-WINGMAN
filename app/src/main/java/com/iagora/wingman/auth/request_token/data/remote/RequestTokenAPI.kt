package com.iagora.wingman.auth.registration.data.remote

import com.iagora.wingman.auth.request_token.data.remote.dto.RequestTokenResponseDTO
import com.iagora.wingman.common.util.Constants
import retrofit2.http.GET
import retrofit2.http.Header

interface RequestTokenAPI {
    @GET("/api/v1/wingman/get-access-token")
    suspend fun getAccessToken(
        @Header(Constants.X_REFRESH_TOKEN) token: String
    ): RequestTokenResponseDTO
}