package com.sdk.foddy.ui.screen

import com.sdk.foddy.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Recipes: BottomBarScreen(
        route = "recipes",
        title = "Recipes",
        icon = R.drawable.baseline_restaurant_menu_24
    )
    object Favorites: BottomBarScreen(
        route = "favorite",
        title = "Favorites",
        icon = R.drawable.baseline_favorite_border_24
    )
    object Settings: BottomBarScreen(
        route = "settings",
        title = "Settings",
        icon = R.drawable.baseline_settings_24
    )
}
