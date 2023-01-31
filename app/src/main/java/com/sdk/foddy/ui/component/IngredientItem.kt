package com.sdk.foddy.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.isFinalState
import com.sdk.data.util.Constants
import com.sdk.domain.model.Ingredient
import com.sdk.foddy.ui.theme.DarkColor
import com.sdk.foddy.ui.theme.ItimFont

@Composable
fun IngredientItem(
    ingredient: Ingredient
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .height(125.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onTertiary)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            rememberCoilPainter(request = "${Constants.BASE_IMAGE_URL}/${ingredient.image}").also {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxSize()
                        .weight(1.1f)
                ) {
                    if (it.loadState.isFinalState()) { // final state, works when state finished / image uploaded
                        Image(
                            painter = it,
                            contentDescription = "Image",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(3.dp)
                        )
                    } else {
                        CircularProgressIndicator(
                            color = DarkColor,
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxSize()
                    .padding(start = 20.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                IngText(
                    text = ingredient.aisle.toString(),
                    size = 16
                )
                IngText(
                    text = ingredient.consistency.lowercase(),
                    size = 14
                )
                IngText(
                    text = "${ingredient.amount} ${ingredient.unit}",
                    size = 14
                )
                IngText(
                    text = ingredient.original,
                    size = 14
                )
            }
        }
    }
}

@Composable
fun IngText(
    text: String,
    size: Int
) {
    Text(
        text = text,
        fontSize = (size).sp,
        color = MaterialTheme.colorScheme.onSecondary,
        maxLines = 1,
        fontFamily = ItimFont,
        overflow = TextOverflow.Ellipsis
    )
}