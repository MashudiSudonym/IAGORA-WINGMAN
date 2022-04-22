package com.iagora.wingman.auth.credential.domain.use_case.set_cache_user_id_use_case

import com.iagora.wingman.auth.credential.domain.repository.CredentialDataStorePreferencesRepository

class SetCacheUserIdUseCase(private val credentialDataStorePreferencesRepository: CredentialDataStorePreferencesRepository) {
    suspend operator fun invoke(token: String) {
        return credentialDataStorePreferencesRepository.setUserId(token)
    }
}