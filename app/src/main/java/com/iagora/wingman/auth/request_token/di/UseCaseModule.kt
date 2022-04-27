package com.iagora.wingman.auth.request_token.di

import com.iagora.wingman.auth.request_token.domain.repository.RequestTokenRepository
import com.iagora.wingman.auth.request_token.domain.use_case.save_token_to_data_store_use_case.SaveTokenToDataStoreUseCase
import com.iagora.wingman.data_store.domain.repository.DataStorePreferencesRepository
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
    fun provideSaveTokenToDataStoreUseCase(
        requestTokenRepository: RequestTokenRepository,
        dataStorePreferencesRepository: DataStorePreferencesRepository
    ): SaveTokenToDataStoreUseCase {
        return SaveTokenToDataStoreUseCase(requestTokenRepository, dataStorePreferencesRepository)
    }
}