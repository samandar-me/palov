package com.sdk.foddy.ui.bottom.favorite

import com.sdk.domain.model.Food

data class FavoriteState(
    val isLoading: Boolean = false,
    val foodList: List<Food> = emptyList()
)
