package com.sdk.foddy.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sdk.foddy.R
import com.sdk.foddy.ui.component.BackgroundImage
import com.sdk.foddy.ui.theme.AppFont
import com.sdk.foddy.util.Graph
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {
    BackgroundImage(image = if (isSystemInDarkTheme()) R.drawable.img else R.drawable.img_4)
    LaunchedEffect(key1 = true) {
        delay(2000L)
        navHostController.navigate(Graph.MAIN) {
            popUpTo(Graph.SPLASH) {
                inclusive = true
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Palov",
            fontSize = 45.sp,
            color = MaterialTheme.colors.onSecondary,
            fontFamily = AppFont
        )
        Spacer(modifier = Modifier.height(180.dp))
    }
}