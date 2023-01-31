package com.sdk.foddy.ui.detail.overview

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.isFinalState
import com.sdk.domain.model.Food
import com.sdk.foddy.R
import com.sdk.foddy.ui.component.DetailChip
import com.sdk.foddy.ui.component.RecipeIcon
import com.sdk.foddy.ui.theme.DarkColor
import com.sdk.foddy.ui.theme.LightGray

@Composable
fun OverviewScreen(
    nullableFood: Food?,
) {
    val food by remember {
        mutableStateOf(nullableFood!!)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val painter = rememberCoilPainter(request = food.image)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    DarkColor
                                )
                            )
                        )
                        .padding(top = 8.dp, bottom = 12.dp, end = 15.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    RecipeIcon(
                        icon = R.drawable.ic_baseline_favorite_24,
                        text = food.likeCount.toString(),
                        iconColor = Color.White
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    RecipeIcon(
                        icon = R.drawable.ic_baseline_access_time_24,
                        text = food.time.toString(),
                        iconColor = Color.White
                    )
                }
            }
        }
        DetailSection(food)
        food.cheap
    }
}

@Composable
fun DetailSection(food: Food) {
    val statistics = mapOf(
        "Vegetarian" to food.vegetarian,
        "Vegan" to food.vegan,
        "Gluten Free" to food.glutenFree,
        "Dairy Free" to food.dairyFree,
        "Healthy" to food.veryHealthy,
        "Cheap" to food.cheap
    )
    Column(modifier = Modifier.padding(10.dp).height(100.dp)) {
        LazyHorizontalGrid(rows = GridCells.Fixed(2)) {
            items(statistics.toList()) {
                DetailChip(text = it.first, isTrue = it.second)
            }
        }
    }
}
