package com.sdk.foddy.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sdk.foddy.ui.component.BottomBar
import com.sdk.foddy.ui.component.CustomTopAppBar
import com.sdk.foddy.ui.component.SearchAppBar
import com.sdk.foddy.ui.screen.BottomBarScreen
import com.sdk.foddy.ui.screen.MainNavGraph
import com.sdk.foddy.util.SearchWidgetState

@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    var title by remember {
        mutableStateOf("")
    }
    var isMain by remember {
        mutableStateOf(false)
    }
    var query by remember {
        mutableStateOf("")
    }
    var searchState by remember {
        mutableStateOf(SearchWidgetState.CLOSED)
    }
    var isRecipe by remember {
        mutableStateOf(true)
    }
    val current = navHostController.currentBackStackEntryAsState()
    when (current.value?.destination?.route) {
        BottomBarScreen.Recipes.route -> {
            isMain = true
            title = "Recipes"
            isRecipe = true
        }
        BottomBarScreen.Favorites.route -> {
            isMain = true
            title = "Favorites"
            isRecipe = false
        }
        BottomBarScreen.Settings.route -> {
            isMain = true
            title = "Settings"
            isRecipe = false
        }
        else -> {
            title = "Details"
            isMain = false
            isRecipe = false
        }
    }
    Scaffold(
        topBar = {
            when (searchState) {
                SearchWidgetState.CLOSED -> {
                    CustomTopAppBar(
                        title = title,
                        isBackIconVisible = !isMain,
                        isSearchIconVisible = isRecipe,
                        onBackClicked = { navHostController.popBackStack() },
                        onSearchClicked = {
                            searchState = SearchWidgetState.OPENED
                        }
                    )
                }
                SearchWidgetState.OPENED -> {
                    if (isRecipe) {
                        SearchAppBar(
                            text = query,
                            onTextChange = { query = it },
                            onCloseClicked = {
                                searchState = SearchWidgetState.CLOSED
                            },
                            onSearchClicked = {

                            },
                            onClearClicked = {
                                query = ""
                            }
                        )
                    } else {
                        searchState = SearchWidgetState.CLOSED
                    }
                }
            }
        },
        bottomBar = {
            BottomBar(navController = navHostController)
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            MainNavGraph(navHostController = navHostController)
        }
    }
}