package com.iagora.wingman.auth.registration.data.remote

import com.iagora.wingman.auth.registration.data.remote.dto.CompleteWingmanDetailDataDTO
import com.iagora.wingman.auth.registration.data.remote.dto.CompleteWingmanDetailResponseDTO
import com.iagora.wingman.common.util.Constants
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface RegistrationAPI {
    @PUT("/api/v1/wingman/wingmanId/complate-wingman-detail")
    suspend fun completeWingmanDetail(
        @Header(Constants.X_REFRESH_TOKEN) token: String,
        @Body completeWingmanDetailData: CompleteWingmanDetailDataDTO
    ): CompleteWingmanDetailResponseDTO

    @Multipart
    @PUT("/api/v1/wingman/wingmanId/complate-wingman-document")
    suspend fun completeWingmanDocument(
        @Header(Constants.X_REFRESH_TOKEN) token: String,
        @Part(Constants.KTP) ktp: MultipartBody.Part,
        @Part(Constants.SKCK) skck: MultipartBody.Part,
        @Part(Constants.BANK) bank: RequestBody,
        @Part(Constants.NUMBER_BALANCE_ACCOUNT) numberBalanceAccount: RequestBody,
        @Part(Constants.NAME_BALANCE_ACCOUNT) nameBalanceAccount: RequestBody,
    ): CompleteWingmanDetailResponseDTO
}