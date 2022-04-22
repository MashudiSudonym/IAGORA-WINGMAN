package com.iagora.wingman.auth.credential.di

import com.iagora.wingman.auth.credential.domain.repository.CredentialDataStorePreferencesRepository
import com.iagora.wingman.auth.credential.domain.use_case.get_cache_token_use_case.GetCacheTokenUseCase
import com.iagora.wingman.auth.credential.domain.use_case.get_cache_user_id_use_case.GetCacheUserIdUseCase
import com.iagora.wingman.auth.credential.domain.use_case.set_cache_token_use_case.SetCacheTokenUseCase
import com.iagora.wingman.auth.credential.domain.use_case.set_cache_user_id_use_case.SetCacheUserIdUseCase
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
    fun provideSetCacheTokenUseCase(credentialDataStorePreferencesRepository: CredentialDataStorePreferencesRepository): SetCacheTokenUseCase {
        return SetCacheTokenUseCase(credentialDataStorePreferencesRepository)
    }

    @Provides
    @Singleton
    fun provideGetCacheTokenUseCase(credentialDataStorePreferencesRepository: CredentialDataStorePreferencesRepository): GetCacheTokenUseCase {
        return GetCacheTokenUseCase(credentialDataStorePreferencesRepository)
    }

    @Provides
    @Singleton
    fun provideSetCacheUserIdUseCase(credentialDataStorePreferencesRepository: CredentialDataStorePreferencesRepository): SetCacheUserIdUseCase {
        return SetCacheUserIdUseCase(credentialDataStorePreferencesRepository)
    }

    @Provides
    @Singleton
    fun provideGetCacheUserIdUseCase(credentialDataStorePreferencesRepository: CredentialDataStorePreferencesRepository): GetCacheUserIdUseCase {
        return GetCacheUserIdUseCase(credentialDataStorePreferencesRepository)
    }
}