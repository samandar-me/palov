package com.sdk.foddy.di

import android.content.Context
import androidx.room.Room
import com.sdk.data.local.database.FoodDao
import com.sdk.data.local.database.FoodDatabase
import com.sdk.data.local.manager.DataStoreManager
import com.sdk.data.remote.network.FoodService
import com.sdk.data.repository.LocalRepositoryImpl
import com.sdk.data.repository.RemoteRepositoryImpl
import com.sdk.data.util.Constants
import com.sdk.domain.repository.LocalRepository
import com.sdk.domain.repository.RemoteRepository
import com.sdk.domain.use_case.base.AllUseCases
import com.sdk.domain.use_case.local.data_store.*
import com.sdk.domain.use_case.local.room.*
import com.sdk.domain.use_case.remote.GetAllRecipesUseCase
import com.sdk.foddy.util.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

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

    @[Provides Singleton]
    fun provideOkhttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @[Provides Singleton]
    fun provideFoodService(
        okHttpClient: OkHttpClient
    ): FoodService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(FoodService::class.java)
    }

    @[Provides Singleton]
    fun provideRemoteRepository(service: FoodService): RemoteRepository {
        return RemoteRepositoryImpl(service)
    }

    @[Provides Singleton]
    fun provideAllUseCases(
        remoteRepository: RemoteRepository,
        localRepository: LocalRepository
    ): AllUseCases {
        return AllUseCases(
            getAllRecipesUseCase = GetAllRecipesUseCase(remoteRepository),
            saveFoodTypeUseCase = SaveFoodTypeUseCase(localRepository),
            getFoodTypeUseCase = GetFoodTypeUseCase(localRepository),
            getFavoriteFoodByIdUseCase = GetFavoriteFoodByIdUseCase(localRepository),
            getFavoriteFoodsUseCase = GetFavoriteFoodsUseCase(localRepository),
            saveFavoriteFoodUseCase = SaveFavoriteFoodUseCase(localRepository),
            deleteFavoriteFoodUseCase = DeleteFavoriteFoodUseCase(localRepository),
            deleteAllFavoriteFoodsUseCase = DeleteAllFavoriteFoodsUseCase(localRepository),
            saveThemeUseCase = SaveThemeUseCase(localRepository),
            getThemeUseCase = GetThemeUseCase(localRepository),
            getUserVisitingUseCase = GetUserVisitingUseCase(localRepository),
            saveUserVisitingUseCase = SaveUserVisitingUseCase(localRepository)
        )
    }

    @[Provides Singleton]
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelper(context)
    }
}