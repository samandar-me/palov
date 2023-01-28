package com.sdk.foddy.ui.bottom.recipes

sealed class RecipeEvent {
    data class OnSearchFood(val query: String): RecipeEvent()
}