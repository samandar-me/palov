package com.sdk.domain.repository

import com.sdk.domain.model.Food
import com.sdk.domain.util.MyResult
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun getAllRecipes(queryMap: Map<String, String>): Flow<MyResult<List<Food>>>
}