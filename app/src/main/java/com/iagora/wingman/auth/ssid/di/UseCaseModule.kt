package com.iagora.wingman.auth.ssid.di

import com.iagora.wingman.auth.ssid.domain.repository.WingmanSSIDRepository
import com.iagora.wingman.auth.ssid.domain.use_case.get_wingman_ssid_use_case.GetWingmanSSIDUseCase
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
    fun provideGetWingmanSSIDUseCase(wingmanSSIDRepository: WingmanSSIDRepository): GetWingmanSSIDUseCase {
        return GetWingmanSSIDUseCase(wingmanSSIDRepository)
    }
}