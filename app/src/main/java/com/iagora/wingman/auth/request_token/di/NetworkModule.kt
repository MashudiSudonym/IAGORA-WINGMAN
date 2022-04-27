package com.iagora.wingman.auth.request_token.di

import com.iagora.wingman.auth.registration.data.remote.RequestTokenAPI
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
    fun provideRequestTokenAPI(retrofit: Retrofit): RequestTokenAPI {
        return retrofit.create(RequestTokenAPI::class.java)
    }
}