package com.sdk.foddy.ui.bottom.recipes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.foddy.R
import com.sdk.foddy.ui.component.*
import com.sdk.foddy.util.SearchWidgetState

@Composable
fun RecipesScreen(navHostController: NavHostController) {
    BackgroundImage(image = R.drawable.img_1)
    val viewModel: RecipeViewModel = hiltViewModel()
    var searchState by remember {
        mutableStateOf(SearchWidgetState.CLOSED)
    }
    LaunchedEffect(key1 = true) {
        viewModel.onEvent(RecipeEvent.OnGetAllRecipes)
    }
    val state  = viewModel.state.value
    if (state.error.isNotEmpty()) {
        println(state.error)
    }
    Column {
        when (searchState) {
            SearchWidgetState.CLOSED -> {
                CustomTopAppBar(
                    title = "Recipes",
                    isBackIconVisible = false,
                    isSearchIconVisible = true,
                    onBackClicked = {  },
                    onSearchClicked = {
                        searchState = SearchWidgetState.OPENED
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
                        onItemClicked = {

                        }
                    )
                }
            }
        }
        if (state.isLoading) {
            LoadingIcon()
        }
    }
}
