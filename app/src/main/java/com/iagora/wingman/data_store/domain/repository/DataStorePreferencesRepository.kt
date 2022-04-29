package com.iagora.wingman.data_store.domain.repository

interface DataStorePreferencesRepository {
    suspend fun setToken(token: String)
    suspend fun getToken(): Result<String>
    suspend fun setUserId(userId: String)
    suspend fun getUserId(): Result<String>
    suspend fun setUserCompleteDataStatus(userCompleteDataStatus: Boolean)
    suspend fun getUserCompleteDataStatus(): Result<Boolean>
}