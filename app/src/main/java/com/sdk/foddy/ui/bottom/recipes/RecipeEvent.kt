package com.sdk.foddy.ui.bottom.recipes

import com.sdk.domain.model.FoodType

sealed class RecipeEvent {
    data class GetAllRecipes(val foodType: FoodType): RecipeEvent()
    data class OnSearchFood(val query: String,): RecipeEvent()
    data class OnSaveFoodType(val foodType: FoodType): RecipeEvent()
    object OnApplyClicked: RecipeEvent()
}