package com.sdk.foddy.ui.bottom.favorite

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.foddy.R
import com.sdk.foddy.ui.component.LoadingIcon
import com.sdk.foddy.ui.component.LottieAnim
import com.sdk.foddy.ui.component.RecipeItem
import com.sdk.foddy.ui.theme.AppFont
import com.sdk.foddy.ui.theme.Purple80
import com.sdk.foddy.util.Graph

@Composable
fun FavoriteScreen(navHostController: NavHostController) {
    val viewModel: FavoriteViewModel = hiltViewModel()
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
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
    if (state.foodList.isNotEmpty()) {
        DeleteFab(
            onDeleteClicked = {
                viewModel.onEvent(FavoriteEvent.OnDeleteAllFavoriteFoods)
            }
        )
    }
}

@Composable
fun DeleteFab(
    onDeleteClicked: () -> Unit
) {
    var isAlertVisible by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = isAlertVisible,
            enter = fadeIn(animationSpec = tween(300)),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            AlertDialog(
//                containerColor = ,
                onDismissRequest = {
                    isAlertVisible = false
                },
                confirmButton = {
                    TextButton(onClick = {
                        onDeleteClicked()
                        isAlertVisible = false
                    }) {
                        Text(text = "Delete", color = MaterialTheme.colorScheme.onSecondary)
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        isAlertVisible = false
                    }) {
                        Text(text = "Cancel", color = MaterialTheme.colorScheme.onSecondary)
                    }
                },
                title = {
                    Text(
                        text = "Delete all favorite foods",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                },
                text = {
                    Text(
                        text = "This action cannot be undone",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                })
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(15.dp),
            onClick = {
                isAlertVisible = true
            },
            containerColor = Purple80,
            contentColor = Color.White
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.Red
            )
        }
    }
}