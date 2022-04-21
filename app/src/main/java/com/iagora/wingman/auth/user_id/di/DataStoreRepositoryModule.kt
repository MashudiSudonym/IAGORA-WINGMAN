package com.iagora.wingman.auth.user_id.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.iagora.wingman.auth.user_id.data.repository.UserIdDataStorePreferencesRepositoryImpl
import com.iagora.wingman.auth.user_id.domain.repository.UserIdDataStorePreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.userIdDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "com.iagora.wingman.user_id_data_store"
)

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserIdDataStorePreferencesRepository(userIdDataStorePreferencesRepositoryImpl: UserIdDataStorePreferencesRepositoryImpl): UserIdDataStorePreferencesRepository

    companion object {
        @Provides
        @Singleton
        fun provideUserIdDataStorePreferences(@ApplicationContext applicationContext: Context): DataStore<Preferences> {
            return applicationContext.userIdDataStore
        }
    }
}