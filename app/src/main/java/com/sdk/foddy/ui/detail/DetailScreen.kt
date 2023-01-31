package com.sdk.foddy.ui.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.sdk.domain.model.Food
import com.sdk.foddy.R
import com.sdk.foddy.ui.component.BackgroundImage
import com.sdk.foddy.ui.detail.ing.IngredientsScreen
import com.sdk.foddy.ui.detail.overview.OverviewScreen
import com.sdk.foddy.ui.theme.AppFont
import com.sdk.foddy.ui.theme.ItimFont

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    food: Food?
) {
    val tabs = listOf("Overview","Ingredients","Instructions")
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Details", color = MaterialTheme.colorScheme.onSecondary, fontFamily = AppFont)
                },
                backgroundColor = MaterialTheme.colorScheme.primary,
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = "Favorite")
                    }
                },
                elevation = 0.dp
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TabRow(
                selectedTabIndex = selectedIndex,
                backgroundColor = MaterialTheme.colorScheme.primary,
                indicator = {
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(it[selectedIndex]),
                        height = 3.dp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            ) {
                tabs.forEachIndexed { index, s ->
                    Tab(
                       selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        text = {
                            Text(text = s, color = MaterialTheme.colorScheme.onSecondary, fontFamily = ItimFont, fontSize = 17.sp)
                        }
                    )
                }
            }
            when(selectedIndex) {
                0 -> OverviewScreen(nullableFood = food)
                1 -> IngredientsScreen(ingredients = food?.ingredients)
                2 -> Text(text = tabs[selectedIndex])
            }
        }
    }
}