package com.iagora.wingman.auth.request_token.di

import com.iagora.wingman.auth.registration.data.remote.RequestTokenAPI
import com.iagora.wingman.auth.request_token.data.repository.RequestTokenRepositoryImpl
import com.iagora.wingman.auth.request_token.domain.repository.RequestTokenRepository
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
    fun provideRequestTokenRepository(requestTokenAPI: RequestTokenAPI): RequestTokenRepository {
        return RequestTokenRepositoryImpl(requestTokenAPI)
    }
}