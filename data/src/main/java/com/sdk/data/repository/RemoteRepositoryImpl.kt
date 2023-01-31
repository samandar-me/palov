package com.sdk.data.repository

import android.util.Log
import com.sdk.data.mapper.toFood
import com.sdk.data.remote.network.FoodService
import com.sdk.domain.repository.RemoteRepository
import com.sdk.domain.util.MyResult
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val service: FoodService
): RemoteRepository {
    override suspend fun getAllRecipes(queryMap: Map<String, String>) = flow {
        emit(MyResult.Loading)
        try {
            val response = service.getAllRecipes(queryMap)
            if (response.isSuccessful) {
                response.body()?.results?.map { it.toFood() }?.let {
                    emit(MyResult.Success(it))
                }
            }
        } catch (e: Exception) {
            emit(MyResult.Error(e.message.toString()))
        }
    }
}