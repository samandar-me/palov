package com.sdk.data.repository

import com.sdk.data.remote.network.FoodService
import com.sdk.domain.model.Food
import com.sdk.domain.repository.RemoteRepository
import com.sdk.domain.util.MyResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val service: FoodService
): RemoteRepository {
    override suspend fun getAllRecipes(queryMap: Map<String, String>): Flow<MyResult<List<Food>>> {

    }

    override suspend fun searchRecipe(query: Map<String, String>): Flow<MyResult<List<Food>>> {

    }
}