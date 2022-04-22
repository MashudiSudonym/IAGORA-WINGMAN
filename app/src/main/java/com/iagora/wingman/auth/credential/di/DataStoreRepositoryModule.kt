package com.iagora.wingman.auth.credential.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.iagora.wingman.auth.credential.data.repository.CredentialDataStorePreferencesRepositoryImpl
import com.iagora.wingman.auth.credential.domain.repository.CredentialDataStorePreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.credentialDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "com.iagora.wingman.auth.credential"
)

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCredentialDataStorePreferencesRepository(
        credentialDataStorePreferencesRepositoryImpl: CredentialDataStorePreferencesRepositoryImpl
    ): CredentialDataStorePreferencesRepository

    companion object {
        @Provides
        @Singleton
        fun provideTokenDataStorePreferences(@ApplicationContext applicationContext: Context): DataStore<Preferences> {
            return applicationContext.credentialDataStore
        }
    }
}