package com.iagora.wingman.auth.ssid.di

import com.iagora.wingman.auth.ssid.data.remote.WingmanSSIDAPI
import com.iagora.wingman.auth.ssid.data.repository.WingmanSSIDRepositoryImpl
import com.iagora.wingman.auth.ssid.domain.repository.WingmanSSIDRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideWingmanSSIDRepository(wingmanSSIDAPI: WingmanSSIDAPI): WingmanSSIDRepository {
        return WingmanSSIDRepositoryImpl(wingmanSSIDAPI)
    }
}