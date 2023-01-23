package com.sdk.foddy.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BackgroundImage(
    image: Int
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "Image",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
}