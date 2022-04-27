package com.iagora.wingman.auth.request_token.presentation

import com.iagora.wingman.auth.request_token.domain.repository.RequestTokenRepository
import com.iagora.wingman.auth.request_token.domain.use_case.save_token_to_data_store_use_case.SaveTokenToDataStoreUseCase
import com.iagora.wingman.data_store.domain.repository.DataStorePreferencesRepository
import javax.inject.Inject

class RequestTokenWorkerDependency @Inject constructor(
    requestTokenRepository: RequestTokenRepository,
    dataStorePreferencesRepository: DataStorePreferencesRepository
) {
    val saveTokenToDataStoreUseCase: SaveTokenToDataStoreUseCase =
        SaveTokenToDataStoreUseCase(requestTokenRepository, dataStorePreferencesRepository)
}