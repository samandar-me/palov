package com.sdk.domain.use_case.base

import com.sdk.domain.use_case.remote.GetAllRecipesUseCase
import com.sdk.domain.use_case.remote.SearchFoodUseCase

data class AllUseCases(
    val getAllRecipesUseCase: GetAllRecipesUseCase,
    val searchFoodUseCase: SearchFoodUseCase
)