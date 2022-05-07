package com.iagora.wingman.auth.registration.di

import com.iagora.wingman.auth.registration.domain.use_case.field_validation_use_case.*
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

    @Provides
    @Singleton
    fun provideBankAccountNumberFieldValidationUseCase(): BankAccountNumberFieldValidationUseCase {
        return BankAccountNumberFieldValidationUseCase()
    }

    @Provides
    @Singleton
    fun provideBankAccountUserNameFieldValidationUseCase(): BankAccountUserNameFieldValidationUseCase {
        return BankAccountUserNameFieldValidationUseCase()
    }

    @Provides
    @Singleton
    fun provideBankNameFieldValidationUseCase(): BankNameFieldValidationUseCase {
        return BankNameFieldValidationUseCase()
    }

    @Provides
    @Singleton
    fun provideUserIdCardImageValidationUseCase(): UserIdCardImageValidationUseCase {
        return UserIdCardImageValidationUseCase()
    }

    @Provides
    @Singleton
    fun provideUserPoliceAgreementLetterImageValidationUseCase(): UserPoliceAgreementLetterImageValidationUseCase {
        return UserPoliceAgreementLetterImageValidationUseCase()
    }
}