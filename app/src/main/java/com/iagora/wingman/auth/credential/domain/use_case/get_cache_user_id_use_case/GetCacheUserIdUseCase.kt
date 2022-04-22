package com.iagora.wingman.auth.credential.domain.use_case.get_cache_user_id_use_case

import com.iagora.wingman.auth.credential.domain.repository.CredentialDataStorePreferencesRepository

class GetCacheUserIdUseCase(private val credentialDataStorePreferencesRepository: CredentialDataStorePreferencesRepository) {
    suspend operator fun invoke(): String {
        val result = credentialDataStorePreferencesRepository.getUserId()
        return result.getOrNull().orEmpty()
    }
}