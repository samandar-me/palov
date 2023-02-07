package com.sdk.foddy.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.foddy.R
import com.sdk.foddy.ui.theme.AppFont
import com.sdk.foddy.util.Graph
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {
    val viewModel: SplashViewModel = hiltViewModel()
    LaunchedEffect(key1 = true) {
        val route = if (viewModel.userState.value) Graph.MAIN else "ON_BOARDING"
        delay(2000L)
        navHostController.navigate(route = route) {
            popUpTo(Graph.SPLASH) {
                inclusive = true
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Palov",
            fontSize = 60.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            fontFamily = AppFont
        )
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "Splash Logo"
        )
    }
}