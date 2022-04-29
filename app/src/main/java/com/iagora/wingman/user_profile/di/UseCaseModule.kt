package com.iagora.wingman.user_profile.di

import com.iagora.wingman.data_store.domain.repository.DataStorePreferencesRepository
import com.iagora.wingman.user_profile.domain.use_case.wingman_logout_use_case.WingmanLogoutUseCase
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
    fun provideWingmanLogoutUseCase(dataStorePreferencesRepository: DataStorePreferencesRepository): WingmanLogoutUseCase {
        return WingmanLogoutUseCase(dataStorePreferencesRepository)
    }
}