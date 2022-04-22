package com.iagora.wingman.auth.credential.domain.use_case.get_cache_token_use_case

import com.iagora.wingman.auth.credential.domain.repository.CredentialDataStorePreferencesRepository

class GetCacheTokenUseCase(private val credentialDataStorePreferencesRepository: CredentialDataStorePreferencesRepository) {
    suspend operator fun invoke(): String {
        val result = credentialDataStorePreferencesRepository.getToken()
        return result.getOrNull().orEmpty()
    }
}