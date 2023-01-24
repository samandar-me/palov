package com.sdk.domain.repository

import com.sdk.domain.model.Food
import com.sdk.domain.util.Response
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun getAllRecipes(queryMap: Map<String,String>): Flow<Response<List<Food>>>
    suspend fun searchRecipe(query: Map<String, String>): Flow<Response<List<Food>>>
}