package com.sdk.foddy.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.sdk.domain.model.Food
import com.sdk.foddy.ui.detail.ing.IngredientsScreen
import com.sdk.foddy.ui.detail.ins.InstructionsScreen
import com.sdk.foddy.ui.detail.overview.OverviewScreen
import com.sdk.foddy.ui.theme.AppFont
import com.sdk.foddy.ui.theme.ItimFont
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    food: Food?
) {
    val tabs = listOf("Overview", "Ingredients", "Instructions")
    val pagerState = rememberPagerState(0)
    val scope = rememberCoroutineScope()
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
                        // save to room this favorite food
                    }) {
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorite"
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
                        modifier = Modifier.tabIndicatorOffset(it[pagerState.currentPage]),
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