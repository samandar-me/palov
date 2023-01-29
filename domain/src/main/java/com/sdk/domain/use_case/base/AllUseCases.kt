package com.sdk.domain.use_case.base

import com.sdk.domain.use_case.local.GetFoodTypeUseCase
import com.sdk.domain.use_case.local.SaveFoodTypeUseCase
import com.sdk.domain.use_case.remote.GetAllRecipesUseCase

data class AllUseCases(
    val getAllRecipesUseCase: GetAllRecipesUseCase,
    val getFoodTypeUseCase: GetFoodTypeUseCase,
    val saveFoodTypeUseCase: SaveFoodTypeUseCase
)