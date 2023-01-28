package com.sdk.data.repository

import com.sdk.data.mapper.toFood
import com.sdk.data.remote.network.FoodService
import com.sdk.domain.model.Food
import com.sdk.domain.repository.RemoteRepository
import com.sdk.domain.util.MyResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val service: FoodService
): RemoteRepository {
    override suspend fun getAllRecipes(queryMap: Map<String, String>): Flow<MyResult<List<Food>>> = flow {
        emit(MyResult.Loading)
        try {
            val response = service.getAllRecipes(queryMap)
            if (response.isSuccessful) {
                val data = response.body()?.results?.map { it.toFood() }!!
                println("@@@${response.body()}")
                emit(MyResult.Success(data))
            }
        } catch (e: Exception) {
            emit(MyResult.Error(e.message.toString()))
        }
    }

    override suspend fun searchRecipe(query: Map<String, String>): Flow<MyResult<List<Food>>> = flow {
        emit(MyResult.Loading)
        try {
            val response = service.searchFood(query)
            if (response.isSuccessful) {
                val data = response.body()?.results?.map { it.toFood() }!!
                emit(MyResult.Success(data))
            }
        } catch (e: Exception) {
            emit(MyResult.Error(e.message.toString()))
        }
    }
}