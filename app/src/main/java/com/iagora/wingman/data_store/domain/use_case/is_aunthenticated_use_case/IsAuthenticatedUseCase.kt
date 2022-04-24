package com.iagora.wingman.data_store.domain.use_case.is_aunthenticated_use_case

import com.iagora.wingman.data_store.domain.repository.DataStorePreferencesRepository
import com.iagora.wingman.common.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IsAuthenticatedUseCase(private val dataStorePreferencesRepository: DataStorePreferencesRepository) {
    suspend operator fun invoke(): Flow<Resource<Boolean>> {
        val getCacheToken = dataStorePreferencesRepository.getToken()
        val getCacheUserId = dataStorePreferencesRepository.getUserId()

        return if (getCacheToken.getOrDefault("").isEmpty() && getCacheUserId.getOrDefault("").isEmpty()) flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(false))
            emit(Resource.Loading(false))
        } else flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(true))
            emit(Resource.Loading(false))
        }
    }
}