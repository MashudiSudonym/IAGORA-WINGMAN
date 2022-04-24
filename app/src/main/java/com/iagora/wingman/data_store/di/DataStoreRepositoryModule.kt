package com.iagora.wingman.data_store.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.iagora.wingman.data_store.data.repository.DataStorePreferencesRepositoryImpl
import com.iagora.wingman.data_store.domain.repository.DataStorePreferencesRepository
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
        dataStorePreferencesRepositoryImpl: DataStorePreferencesRepositoryImpl
    ): DataStorePreferencesRepository

    companion object {
        @Provides
        @Singleton
        fun provideTokenDataStorePreferences(@ApplicationContext applicationContext: Context): DataStore<Preferences> {
            return applicationContext.credentialDataStore
        }
    }
}