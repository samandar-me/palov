package com.sdk.foddy.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.isFinalState

@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    url: String,
    contentScale: ContentScale = ContentScale.FillBounds,
    indicatorWidth: Int = 4,
    indicatorSize: Int = 20,
    imagePadding: Int = 0
) {
    rememberCoilPainter(request = url).also {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
        ) {
            if (it.loadState.isFinalState()) { // final state, works when state finished / image uploaded
                Image(
                    painter = it,
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding((imagePadding).dp),
                    contentScale = contentScale
                )
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.size((indicatorSize).dp),
                    color = MaterialTheme.colorScheme.onSecondary,
                    strokeWidth = (indicatorWidth).dp
                )
            }
        }
    }
}