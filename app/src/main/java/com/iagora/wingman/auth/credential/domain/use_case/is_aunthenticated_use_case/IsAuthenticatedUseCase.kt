package com.iagora.wingman.auth.credential.domain.use_case.is_aunthenticated_use_case

import com.iagora.wingman.auth.credential.domain.repository.CredentialDataStorePreferencesRepository
import com.iagora.wingman.common.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IsAuthenticatedUseCase(private val credentialDataStorePreferencesRepository: CredentialDataStorePreferencesRepository) {
    suspend operator fun invoke(): Flow<Resource<Boolean>> {
        val getCacheToken = credentialDataStorePreferencesRepository.getToken()
        val getCacheUserId = credentialDataStorePreferencesRepository.getUserId()

        return if (getCacheToken.getOrNull().isNullOrEmpty() && getCacheUserId.getOrNull().isNullOrEmpty()) {
            flow {
                emit(Resource.Loading(true))
                emit(Resource.Success(true))
                emit(Resource.Loading(false))
            }
        } else {
            flow {
                emit(Resource.Loading(true))
                emit(Resource.Success(false))
                emit(Resource.Loading(false))
            }
        }
    }
}