package com.sdk.foddy.ui.detail.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.sdk.domain.model.Food
import com.sdk.foddy.ui.detail.tabs.*
import com.sdk.foddy.ui.theme.AppFont
import com.sdk.foddy.ui.theme.ItimFont
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    food: Food?,
) {
    val viewModel: DetailViewModel = hiltViewModel()
    val tabs = listOf("Overview", "Ingredients", "Instructions")
    val pagerState = rememberPagerState(0) // horizontal pager state
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        food?.let {
            viewModel.onEvent(DetailEvent.OnCheckFood(it.foodId))
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Details",
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontFamily = AppFont
                    )
                },
                backgroundColor = MaterialTheme.colorScheme.primary,
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        food?.let {
                            viewModel.onEvent(DetailEvent.OnUpdateFood(it))
                        }
                    }) {
                        Icon(
                            imageVector = if (viewModel.isFoodSaved.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (viewModel.isFoodSaved.value) Color.Red else MaterialTheme.colorScheme.onSecondary
                        )
                    }
                },
                elevation = 0.dp
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                backgroundColor = MaterialTheme.colorScheme.primary,
                indicator = {
                    TabRowDefaults.Indicator(
                        modifier = Modifier.pagerTabIndicatorOffset(
                            pagerState,
                            it
                        ), // tab indicator
                        height = 3.dp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            ) {
                tabs.forEachIndexed { index, s ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Text(
                                text = s,
                                color = MaterialTheme.colorScheme.onSecondary,
                                fontFamily = ItimFont,
                                fontSize = 17.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }
            }
            HorizontalPager(count = 3, state = pagerState) {
                when (it) {
                    0 -> OverviewScreen(nullableFood = food)
                    1 -> IngredientsScreen(ingredients = food?.ingredients)
                    2 -> InstructionsScreen(steps = food?.analyzedIns?.get(0)?.steps)
                }
            }
        }
    }
}