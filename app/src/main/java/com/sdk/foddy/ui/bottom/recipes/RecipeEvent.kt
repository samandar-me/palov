package com.sdk.foddy.ui.bottom.recipes

import com.sdk.domain.model.FoodType

sealed class RecipeEvent {
    data class OnSearchFood(val query: String): RecipeEvent()
    data class OnSaveFoodType(val foodType: FoodType): RecipeEvent()
    object OnApplyClicked: RecipeEvent()
}