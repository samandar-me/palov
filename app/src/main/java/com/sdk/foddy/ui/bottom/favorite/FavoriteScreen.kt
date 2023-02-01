package com.sdk.foddy.ui.bottom.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.foddy.R
import com.sdk.foddy.ui.component.LoadingIcon
import com.sdk.foddy.ui.component.LottieAnim
import com.sdk.foddy.ui.component.RecipeItem
import com.sdk.foddy.ui.theme.AppFont
import com.sdk.foddy.util.Graph

@Composable
fun FavoriteScreen(navHostController: NavHostController) {
    val viewModel: FavoriteViewModel = hiltViewModel()
    val state = viewModel.state.value
    Column(
        modifier = Modifier.fillMaxSize().background(
            color = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Favorites",
                    fontFamily = AppFont,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            },
            backgroundColor = MaterialTheme.colorScheme.primary,
        )
        LazyColumn(
            contentPadding = PaddingValues(all = 5.dp)
        ) {
            items(
                items = state.foodList,
                key = { it.foodId }
            ) {
                RecipeItem(
                    food = it,
                    onItemClicked = { food ->
                        navHostController.currentBackStackEntry?.savedStateHandle?.set(
                            "food",
                            food
                        )
                        navHostController.navigate(Graph.DETAIL)
                    }
                )
            }
        }
        if (state.isLoading) {
            LoadingIcon()
        }
        if (!state.isLoading && state.foodList.isEmpty()) {
            LottieAnim(anim = R.raw.fast_food)
        }
    }
}