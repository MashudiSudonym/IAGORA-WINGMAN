package com.iagora.wingman.data_store.di

import com.iagora.wingman.auth.request_token.domain.repository.RequestTokenRepository
import com.iagora.wingman.auth.request_token.domain.use_case.save_token_to_data_store_use_case.SaveTokenToDataStoreUseCase
import com.iagora.wingman.data_store.domain.repository.DataStorePreferencesRepository
import com.iagora.wingman.data_store.domain.use_case.is_aunthenticated_use_case.IsAuthenticatedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideIsAuthenticatedUseCase(
        saveTokenToDataStoreUseCase: SaveTokenToDataStoreUseCase,
        dataStorePreferencesRepository: DataStorePreferencesRepository,
        requestTokenRepository: RequestTokenRepository
    ): IsAuthenticatedUseCase {
        return IsAuthenticatedUseCase(
            saveTokenToDataStoreUseCase,
            dataStorePreferencesRepository,
            requestTokenRepository
        )
    }
}