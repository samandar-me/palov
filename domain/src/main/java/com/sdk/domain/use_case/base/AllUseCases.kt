package com.sdk.domain.use_case.base

import com.sdk.domain.use_case.local.data_store.*
import com.sdk.domain.use_case.local.room.*
import com.sdk.domain.use_case.remote.GetAllRecipesUseCase

data class AllUseCases(
    val getAllRecipesUseCase: GetAllRecipesUseCase,
    val getFoodTypeUseCase: GetFoodTypeUseCase,
    val saveFoodTypeUseCase: SaveFoodTypeUseCase,
    val deleteFavoriteFoodUseCase: DeleteFavoriteFoodUseCase,
    val getFavoriteFoodByIdUseCase: GetFavoriteFoodByIdUseCase,
    val getFavoriteFoodsUseCase: GetFavoriteFoodsUseCase,
    val saveFavoriteFoodUseCase: SaveFavoriteFoodUseCase,
    val deleteAllFavoriteFoodsUseCase: DeleteAllFavoriteFoodsUseCase,
    val saveThemeUseCase: SaveThemeUseCase,
    val getThemeUseCase: GetThemeUseCase
)