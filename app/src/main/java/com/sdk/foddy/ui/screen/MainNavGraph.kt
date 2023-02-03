package com.sdk.foddy.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sdk.domain.model.Food
import com.sdk.foddy.ui.bottom.favorite.FavoriteScreen
import com.sdk.foddy.ui.bottom.recipes.RecipesScreen
import com.sdk.foddy.ui.bottom.settings.SettingsScreen
import com.sdk.foddy.ui.detail.main.DetailScreen
import com.sdk.foddy.ui.onboarding.MainPager
import com.sdk.foddy.ui.splash.SplashScreen
import com.sdk.foddy.util.Graph

fun NavGraphBuilder.splashNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.SPLASH,
        startDestination = "SPLASH"
    ) {
        composable(
            route = "SPLASH"
        ) {
            SplashScreen(navHostController)
        }
        composable(route = "ON_BOARDING") {
            MainPager(navHostController = navHostController)
        }
    }
}

@Composable
fun MainNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Recipes.route
    ) {
        composable(route = BottomBarScreen.Recipes.route) {
            RecipesScreen(navHostController)
        }
        composable(route = BottomBarScreen.Favorites.route) {
            FavoriteScreen(navHostController)
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen()
        }
        detailsNavGraph(navHostController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.DETAIL,
        startDestination = "detail"
    ) {
        composable(
            route = "detail"
        ) {
            val food =
                navHostController.previousBackStackEntry?.savedStateHandle?.get<Food>("food")
            DetailScreen(navHostController = navHostController, food = food)
        }
    }
}