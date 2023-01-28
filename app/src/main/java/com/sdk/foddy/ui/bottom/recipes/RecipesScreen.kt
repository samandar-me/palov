package com.sdk.foddy.ui.bottom.recipes

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.sdk.foddy.R
import com.sdk.foddy.ui.component.*
import com.sdk.foddy.ui.theme.AppFont
import com.sdk.foddy.util.Graph
import com.sdk.foddy.util.SearchWidgetState

@Composable
fun RecipesScreen(navHostController: NavHostController) {
    BackgroundImage(image = R.drawable.img_1)
    val viewModel: RecipeViewModel = hiltViewModel()
    var searchState by remember {
        mutableStateOf(SearchWidgetState.CLOSED)
    }
    val state = viewModel.state.value
    Column {
        when (searchState) {
            SearchWidgetState.CLOSED -> {
                TopAppBar(
                    title = {
                        Text(
                            text = "Recipes",
                            fontFamily = AppFont,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    },
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    actions = {
                        IconButton(onClick = {
                            searchState = SearchWidgetState.OPENED
                        }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                        }
                    }
                )
            }
            SearchWidgetState.OPENED -> {
                SearchAppBar(
                    onCloseClicked = {
                        searchState = SearchWidgetState.CLOSED
                    },
                    onSearchClicked = {
                        viewModel.onEvent(RecipeEvent.OnSearchFood(it))
                    }
                )
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(all = 5.dp)
        ) {
            if (state.isLoading.not()) {
                items(
                    items = state.success,
                    key = { it.id }
                ) {
                    RecipeItem(
                        food = it,
                        onItemClicked = { food ->
                            navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                key = "food",
                                value = food
                            )
                            navHostController.navigate(Graph.DETAIL)
                        }
                    )
                }
            }
        }
        if (state.error.isNotEmpty()) {
            ErrorMessage(state.error)
        }
        if (state.isLoading) {
            LoadingIcon()
        }
    }
}
