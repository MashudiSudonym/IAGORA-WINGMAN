package com.iagora.wingman.auth.registration.data.remote

import com.iagora.wingman.auth.registration.data.remote.dto.CompleteWingmanDetailDataDTO
import com.iagora.wingman.auth.registration.data.remote.dto.CompleteWingmanDetailResponseDTO
import com.iagora.wingman.common.util.Constants
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT

interface RegistrationAPI {
    @PUT("/api/v1/wingman/wingmanId/complate-wingman-detail")
    suspend fun completeWingmanDetail(
        @Header(Constants.X_REFRESH_TOKEN) token: String,
        @Body completeWingmanDetailData: CompleteWingmanDetailDataDTO
    ): CompleteWingmanDetailResponseDTO
}