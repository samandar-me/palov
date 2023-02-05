package com.sdk.foddy.di

import android.content.Context
import androidx.room.Room
import com.sdk.data.local.database.FoodDao
import com.sdk.data.local.database.FoodDatabase
import com.sdk.data.local.manager.DataStoreManager
import com.sdk.data.repository.LocalRepositoryImpl
import com.sdk.domain.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestDatabaseModule {

    @[Provides Singleton]
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }
    @[Provides Singleton]
    fun provideLocalRepository(manager: DataStoreManager,dao: FoodDao): LocalRepository {
        return LocalRepositoryImpl(manager, dao)
    }

    @[Provides Singleton]
    fun provideFoodDatabase(
        @ApplicationContext context: Context
    ): FoodDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            FoodDatabase::class.java,
        ).build()
    }
    @[Provides Singleton]
    fun providesFoodDao(database: FoodDatabase): FoodDao {
        return database.dao
    }
}