package com.iagora.wingman.auth.registration.di

import com.iagora.wingman.auth.registration.domain.use_case.field_validation_use_case.AddressFieldValidationUseCase
import com.iagora.wingman.auth.registration.domain.use_case.field_validation_use_case.CityFieldValidationUseCase
import com.iagora.wingman.auth.registration.domain.use_case.field_validation_use_case.EmailFieldValidationUseCase
import com.iagora.wingman.auth.registration.domain.use_case.field_validation_use_case.NameFieldValidationUseCase
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
    fun provideNameFieldValidationUseCase(): NameFieldValidationUseCase {
        return NameFieldValidationUseCase()
    }

    @Provides
    @Singleton
    fun provideEmailFieldValidationUseCase(): EmailFieldValidationUseCase {
        return EmailFieldValidationUseCase()
    }

    @Provides
    @Singleton
    fun provideAddressFieldValidationUseCase(): AddressFieldValidationUseCase {
        return AddressFieldValidationUseCase()
    }

    @Provides
    @Singleton
    fun provideCityFieldValidationUseCase(): CityFieldValidationUseCase {
        return CityFieldValidationUseCase()
    }
}