package com.iagora.wingman.auth.otp.domain.use_case.is_wingman_complete_data_use_case

import com.iagora.wingman.data_store.domain.repository.DataStorePreferencesRepository

class IsWingmanCompleteDataUseCase(private val dataStorePreferencesRepository: DataStorePreferencesRepository) {
    suspend operator fun invoke(): Boolean {
        return dataStorePreferencesRepository.getUserCompleteDataStatus().getOrDefault(false)
    }
}