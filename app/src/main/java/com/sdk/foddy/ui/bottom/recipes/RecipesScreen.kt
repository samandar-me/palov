package com.sdk.foddy.ui.bottom.recipes

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.foddy.MainActivity
import com.sdk.foddy.R
import com.sdk.foddy.ui.component.*
import com.sdk.foddy.ui.theme.Purple80
import com.sdk.foddy.util.Graph
import com.sdk.foddy.util.SearchWidgetState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun RecipesScreen(navHostController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current as MainActivity
    val viewModel: RecipeViewModel = hiltViewModel()
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = viewModel.firstTime) {
        if (viewModel.firstTime) {
            viewModel.onEvent(RecipeEvent.GetAllRecipes(viewModel.foodState.value))
            viewModel.firstTime = false
        }
    }
    var searchState by remember {
        mutableStateOf(SearchWidgetState.CLOSED)
    }
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val state = viewModel.state.value

    BackHandler {
        if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
            coroutineScope.launch {
                bottomSheetScaffoldState.bottomSheetState.collapse()
            }
        } else {
            context.finish()
        }
    }

    BottomSheetScaffold(
        modifier = Modifier.semantics {
            testTag = "RecipeBackground"
        },
        topBar = {
            MainTopBar(
                searchWidgetState = searchState,
                onActionClicked = {
                    searchState = SearchWidgetState.OPENED
                    keyboardController?.show()
                },
                onCloseClicked = {
                    searchState = SearchWidgetState.CLOSED
                    keyboardController?.hide()
                },
                onSearchClicked = {
                    viewModel.onEvent(RecipeEvent.OnSearchFood(it))
                }
            )
        },
        sheetContent = {
            SheetContent(
                foodState = viewModel.foodState.value,
                onChipClicked = { foodType ->
                    viewModel.onEvent(RecipeEvent.OnSaveFoodType(foodType))
                },
                onApplyClicked = {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                        viewModel.onEvent(RecipeEvent.OnApplyClicked)
                    }
                }
            )
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp
    ) {
        Surface(
            modifier =
            Modifier.blur(if (bottomSheetScaffoldState.bottomSheetState.isExpanded) 5.dp else 0.dp),
            color = androidx.compose.material3.MaterialTheme.colorScheme.secondaryContainer
        ) {
            LazyColumn(
                contentPadding = PaddingValues(all = 5.dp),
                modifier = Modifier.semantics {
                    testTag = "RecipeScreenLazyColumn"
                }
            ) {
                if (state.isLoading.not()) {
                    items(
                        items = state.success,
                        key = { it.foodId }
                    ) {
                        RecipeItem(
                            food = it,
                            onItemClicked = { food ->
                                if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
                                    coroutineScope.launch {
                                        bottomSheetScaffoldState.bottomSheetState.collapse()
                                    }
                                } else {
                                    navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                        "food",
                                        food
                                    )
                                    navHostController.navigate(Graph.DETAIL)
                                }
                            }
                        )
                    }
                }
            }
            BottomSection(
                onFabClicked = {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    }
                },
                isLoading = state.isLoading,
                error = state.error,
                isListEmpty = state.success.isEmpty()
            )
        }
    }
}

@Composable
fun BottomSection(
    onFabClicked: () -> Unit,
    error: String,
    isLoading: Boolean,
    isListEmpty: Boolean,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = onFabClicked,
            containerColor = Purple80,
            contentColor = Color.White
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_restaurant_menu_24),
                contentDescription = "MealFab"
            )
        }
    }
    if (error.isNotBlank() && isListEmpty) {
        ErrorMessage(error)
    }
    if (isLoading) {
        LoadingIcon()
    }
}
