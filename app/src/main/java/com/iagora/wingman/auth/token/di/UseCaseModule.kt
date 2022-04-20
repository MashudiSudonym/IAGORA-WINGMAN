package com.iagora.wingman.auth.token.di

import com.iagora.wingman.auth.token.domain.repository.TokenDataStorePreferencesRepository
import com.iagora.wingman.auth.token.domain.use_case.get_cache_token_use_case.GetCacheTokenUseCase
import com.iagora.wingman.auth.token.domain.use_case.set_cache_token_use_case.SetCacheTokenUseCase
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
    fun provideSetCacheTokenUseCase(tokenDataStorePreferencesRepository: TokenDataStorePreferencesRepository): SetCacheTokenUseCase {
        return SetCacheTokenUseCase(tokenDataStorePreferencesRepository)
    }

    @Provides
    @Singleton
    fun provideGetCacheTokenUseCase(tokenDataStorePreferencesRepository: TokenDataStorePreferencesRepository): GetCacheTokenUseCase {
        return GetCacheTokenUseCase(tokenDataStorePreferencesRepository)
    }
}