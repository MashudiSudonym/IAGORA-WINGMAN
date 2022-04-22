package com.iagora.wingman.auth.credential.di

import com.iagora.wingman.auth.credential.domain.repository.CredentialDataStorePreferencesRepository
import com.iagora.wingman.auth.credential.domain.use_case.is_aunthenticated_use_case.IsAuthenticatedUseCase
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
    fun provideIsAuthenticatedUseCase(credentialDataStorePreferencesRepository: CredentialDataStorePreferencesRepository): IsAuthenticatedUseCase {
        return IsAuthenticatedUseCase(credentialDataStorePreferencesRepository)
    }
}