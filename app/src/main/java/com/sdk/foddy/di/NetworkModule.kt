package com.sdk.foddy.di

import android.content.Context
import com.sdk.data.local.manager.DataStoreManager
import com.sdk.data.remote.network.FoodService
import com.sdk.data.repository.LocalRepositoryImpl
import com.sdk.data.repository.RemoteRepositoryImpl
import com.sdk.data.util.Constants
import com.sdk.domain.repository.LocalRepository
import com.sdk.domain.repository.RemoteRepository
import com.sdk.domain.use_case.base.AllUseCases
import com.sdk.domain.use_case.local.GetFoodTypeUseCase
import com.sdk.domain.use_case.local.SaveFoodTypeUseCase
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

@Module(includes = [DatabaseModule::class])
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkhttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    @Provides
    @Singleton
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
    @Singleton
    @Provides
    fun provideRemoteRepository(service: FoodService): RemoteRepository {
        return RemoteRepositoryImpl(service)
    }
    @Singleton
    @Provides
    fun provideLocalRepository(manager: DataStoreManager): LocalRepository {
        return LocalRepositoryImpl(manager)
    }
    @Singleton
    @Provides
    fun provideAllUseCases(
        remoteRepository: RemoteRepository,
        localRepository: LocalRepository
    ): AllUseCases {
        return AllUseCases(
            getAllRecipesUseCase = GetAllRecipesUseCase(remoteRepository),
            saveFoodTypeUseCase = SaveFoodTypeUseCase(localRepository),
            getFoodTypeUseCase = GetFoodTypeUseCase(localRepository)
        )
    }
    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelper(context)
    }
}