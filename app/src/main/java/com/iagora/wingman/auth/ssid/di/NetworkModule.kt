package com.iagora.wingman.auth.ssid.di

import com.iagora.wingman.auth.ssid.data.remote.WingmanSSIDAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideWingmanSSIDAPI(retrofit: Retrofit): WingmanSSIDAPI {
        return retrofit.create(WingmanSSIDAPI::class.java)
    }
}