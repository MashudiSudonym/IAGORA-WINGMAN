package com.iagora.wingman.auth.registration.domain.repository

import com.iagora.wingman.auth.registration.domain.model.CompleteWingmanDetailResponse
import com.iagora.wingman.common.util.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface RegistrationRepository {
    suspend fun putWingmanDetail(
        name: String,
        email: String,
        address: String,
        city: String,
        token: String,
    ): Flow<Resource<CompleteWingmanDetailResponse>>

    suspend fun putWingmanDocument(
        ktp: MultipartBody.Part,
        skck: MultipartBody.Part,
        bank: RequestBody,
        numberBalanceAccount: RequestBody,
        nameBalanceAccount: RequestBody,
        token: String,
    ): Flow<Resource<CompleteWingmanDetailResponse>>
}