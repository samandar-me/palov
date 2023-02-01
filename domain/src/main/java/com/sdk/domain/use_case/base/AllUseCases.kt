package com.sdk.domain.use_case.base

import com.sdk.domain.use_case.local.data_store.*
import com.sdk.domain.use_case.local.room.DeleteFavoriteFoodUseCase
import com.sdk.domain.use_case.local.room.GetFavoriteFoodByIdUseCase
import com.sdk.domain.use_case.local.room.GetFavoriteFoodsUseCase
import com.sdk.domain.use_case.local.room.SaveFavoriteFoodUseCase
import com.sdk.domain.use_case.remote.GetAllRecipesUseCase

data class AllUseCases(
    val getAllRecipesUseCase: GetAllRecipesUseCase,
    val getFoodTypeUseCase: GetFoodTypeUseCase,
    val saveFoodTypeUseCase: SaveFoodTypeUseCase,
    val deleteFavoriteFoodUseCase: DeleteFavoriteFoodUseCase,
    val getFavoriteFoodByIdUseCase: GetFavoriteFoodByIdUseCase,
    val getFavoriteFoodsUseCase: GetFavoriteFoodsUseCase,
    val saveFavoriteFoodUseCase: SaveFavoriteFoodUseCase
)