package com.sdk.foddy.ui.bottom.recipes

sealed class RecipeEvent {
    object OnGetAllRecipes: RecipeEvent()
    data class OnSearchFood(val query: String): RecipeEvent()
}