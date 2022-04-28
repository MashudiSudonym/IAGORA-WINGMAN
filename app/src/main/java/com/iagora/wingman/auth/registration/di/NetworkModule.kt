package com.iagora.wingman.auth.registration.di

import com.iagora.wingman.auth.registration.data.remote.RegistrationAPI
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
    fun provideOtpAPI(retrofit: Retrofit): RegistrationAPI {
        return retrofit.create(RegistrationAPI::class.java)
    }
}