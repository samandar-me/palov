package com.sdk.foddy.ui.bottom.recipes

import com.sdk.domain.model.Food
import com.sdk.domain.model.FoodType

data class RecipesState(
    val isLoading: Boolean = false,
    val error: String = "",
    val success: List<Food> = emptyList()
)
