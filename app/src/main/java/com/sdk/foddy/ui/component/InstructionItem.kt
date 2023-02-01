package com.sdk.foddy.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.sdk.data.util.Constants
import com.sdk.domain.model.InsIngredient
import com.sdk.domain.model.InsStep
import com.sdk.foddy.ui.theme.ItimFont

@Composable
fun InstructionItem(
    insStep: InsStep,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        color = MaterialTheme.colorScheme.onTertiary
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            AppText(
                text = "Step ${insStep.number}.",
                size = 22,
                color = MaterialTheme.colorScheme.onSecondary,
                fontFamily = ItimFont,
                maxLine = Int.MAX_VALUE
            )
            Spacer(modifier = Modifier.height(6.dp))
            AppText(
                text = insStep.step,
                size = 18,
                color = MaterialTheme.colorScheme.onSecondary,
                fontFamily = ItimFont,
                maxLine = Int.MAX_VALUE
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                items(insStep.ingredients) {
                    InsIngredientItem(insIngredient = it)
                }
            }
        }
    }
}

@Composable
fun InsIngredientItem(
    insIngredient: InsIngredient,
) {
    Column(
        modifier = Modifier.padding(horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppText(
            text = insIngredient.name,
            size = 15,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.height(3.dp))
        AppImage(
            url = "${Constants.BASE_IMAGE_URL}/${insIngredient.image}",
            modifier = Modifier.size(64.dp),
            contentScale = ContentScale.Fit,
            indicatorWidth = 2
        )
    }
}