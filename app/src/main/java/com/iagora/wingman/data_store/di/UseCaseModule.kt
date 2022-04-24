package com.iagora.wingman.data_store.di

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
    fun provideIsAuthenticatedUseCase(dataStorePreferencesRepository: DataStorePreferencesRepository): IsAuthenticatedUseCase {
        return IsAuthenticatedUseCase(dataStorePreferencesRepository)
    }
}