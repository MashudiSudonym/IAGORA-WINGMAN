package com.iagora.wingman.data_store.domain.repository

interface DataStorePreferencesRepository {
    suspend fun setToken(token: String)
    suspend fun getToken(): Result<String>
    suspend fun setUserId(token: String)
    suspend fun getUserId(): Result<String>
}