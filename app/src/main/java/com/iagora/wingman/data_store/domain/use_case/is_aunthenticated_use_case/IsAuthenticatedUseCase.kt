package com.iagora.wingman.data_store.domain.use_case.is_aunthenticated_use_case

import com.iagora.wingman.auth.request_token.domain.repository.RequestTokenRepository
import com.iagora.wingman.auth.request_token.domain.use_case.save_token_to_data_store_use_case.SaveTokenToDataStoreUseCase
import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.data_store.domain.AuthenticationStatus
import com.iagora.wingman.data_store.domain.repository.DataStorePreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class IsAuthenticatedUseCase(
    private val saveTokenToDataStoreUseCase: SaveTokenToDataStoreUseCase,
    private val dataStorePreferencesRepository: DataStorePreferencesRepository,
    private val requestTokenRepository: RequestTokenRepository
) {
    suspend operator fun invoke(): Flow<Resource<AuthenticationStatus>> {
        val getCacheToken = dataStorePreferencesRepository.getToken()
        val getCacheUserId = dataStorePreferencesRepository.getUserId()
        val requestToken = requestTokenRepository.requestToken(getCacheToken.getOrDefault(""))

        return if (getCacheToken.getOrDefault("").isEmpty() && getCacheUserId.getOrDefault(
                ""
            ).isEmpty()
        ) flow {
            requestToken.collect { result ->
                when (result) {
                    is Resource.Error -> {
                        emit(Resource.Success(AuthenticationStatus(isAuthenticatedStatus = false)))
                        emit(Resource.Loading(false))
                    }
                    is Resource.Loading -> emit(Resource.Loading(true))
                    is Resource.Success -> {
                        saveTokenToDataStoreUseCase().collect {
                            when (it) {
                                is Resource.Error -> {
                                    emit(Resource.Loading(true))
                                    emit(
                                        Resource.Success(
                                            AuthenticationStatus(
                                                isAuthenticatedStatus = false
                                            )
                                        )
                                    )
                                    emit(Resource.Loading(false))
                                }
                                is Resource.Loading -> emit(Resource.Loading(true))
                                is Resource.Success -> {
                                    emit(Resource.Loading(true))
                                    emit(
                                        Resource.Success(
                                            AuthenticationStatus(
                                                isAuthenticatedStatus = true
                                            )
                                        )
                                    )
                                    emit(Resource.Loading(false))
                                }
                            }
                        }
                    }
                }
            }
        } else flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(AuthenticationStatus(isAuthenticatedStatus = true)))
            emit(Resource.Loading(false))
        }
    }
}