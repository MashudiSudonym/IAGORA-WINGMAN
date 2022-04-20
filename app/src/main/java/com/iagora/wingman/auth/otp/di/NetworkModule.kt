package com.iagora.wingman.auth.otp.di

import com.iagora.wingman.auth.otp.data.remote.OtpAPI
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
    fun provideOtpAPI(retrofit: Retrofit): OtpAPI {
        return retrofit.create(OtpAPI::class.java)
    }
}