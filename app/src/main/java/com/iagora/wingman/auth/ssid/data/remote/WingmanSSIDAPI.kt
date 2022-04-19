package com.iagora.wingman.auth.ssid.data.remote

import com.iagora.wingman.common.util.Constants
import com.iagora.wingman.common.util.SimpleResource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface WingmanSSIDAPI {
    @GET("/api/v1/auth/get-token")
    suspend fun getWingmanSSID(
        @Header(Constants.AUTH_KEY) auth: String,
    ): Response<SimpleResource>
}