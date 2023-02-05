package com.sdk.domain.data.repository

import com.sdk.domain.model.Food
import com.sdk.domain.repository.RemoteRepository
import com.sdk.domain.util.MyResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRemoteRepository : RemoteRepository {
    val foodList = mutableListOf<Food>()

    init {
        foodList.add(
            Food(
                id = 0,
                foodId = 1,
                title = "Blah",
                image = "https://image.jpg",
                description = "Hehe",
                likeCount = 100,
                time = 1000,
                vegan = false,
                vegetarian = false,
                veryHealthy = true,
                dairyFree = false,
                cheap = false,
                glutenFree = true,
                ingredients = emptyList(),
                analyzedIns = emptyList()
            )
        )
    }

    override suspend fun getAllRecipes(queryMap: Map<String, String>): Flow<MyResult<List<Food>>> {
        return flow {
            if (queryMap.isEmpty()) {
                emit(MyResult.Error("Query map shouldn't be empty!"))
            } else {
                emit(MyResult.Success(foodList))
            }
        }
    }
}