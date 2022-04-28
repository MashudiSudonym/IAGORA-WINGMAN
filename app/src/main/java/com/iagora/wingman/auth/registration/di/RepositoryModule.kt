package com.iagora.wingman.auth.registration.di

import com.iagora.wingman.auth.registration.data.remote.RegistrationAPI
import com.iagora.wingman.auth.registration.data.repository.RegistrationRepositoryImpl
import com.iagora.wingman.auth.registration.domain.repository.RegistrationRepository
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
    fun provideRegistrationRepository(registrationAPI: RegistrationAPI): RegistrationRepository {
        return RegistrationRepositoryImpl(registrationAPI)
    }
}