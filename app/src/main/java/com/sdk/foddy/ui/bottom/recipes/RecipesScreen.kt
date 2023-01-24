package com.sdk.foddy.ui.bottom.recipes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.foddy.R
import com.sdk.foddy.ui.component.BackgroundImage
import com.sdk.foddy.ui.component.LoadingIcon
import com.sdk.foddy.util.Graph

@Composable
fun RecipesScreen(navHostController: NavHostController) {
    BackgroundImage(image = R.drawable.img_1)
    val viewModel: RecipeViewModel = hiltViewModel()
    val state = remember {
        viewModel.state.value
    }
    if (state.isLoading) {
        LoadingIcon()
    }
    if (state.error.isNotEmpty()) {
        println("@@@${state.error}")
    }
    LazyColumn {
        items(state.success) {
            println("@@@$it")
        }
    }
}