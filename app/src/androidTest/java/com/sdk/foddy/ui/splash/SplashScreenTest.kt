package com.sdk.foddy.ui.splash

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.sdk.foddy.MainActivity
import com.sdk.foddy.di.DatabaseModule
import com.sdk.foddy.di.NetworkModule
import com.sdk.foddy.ui.screen.RootNavigation
import com.sdk.foddy.ui.theme.FooddyTheme
import com.sdk.foddy.util.Graph
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(DatabaseModule::class, NetworkModule::class)
class SplashScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()
    private lateinit var navHostController: TestNavHostController

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            navHostController = TestNavHostController(LocalContext.current)
            FooddyTheme(darkTheme = true) {
                navHostController.navigatorProvider.addNavigator(ComposeNavigator())
                RootNavigation(navHostController = navHostController)
            }
        }
    }

    @Test
    fun checkTheme_and_TitleWithImage() {
        composeRule.onNodeWithText("Palov").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Splash Logo").assertIsDisplayed()
    }

    @Test
    fun ifUserAlreadyVisited_navigateToMainScreen() {
        val route = navHostController.currentBackStackEntry?.destination?.route
        Assert.assertEquals(route, Graph.MAIN)
    }
}