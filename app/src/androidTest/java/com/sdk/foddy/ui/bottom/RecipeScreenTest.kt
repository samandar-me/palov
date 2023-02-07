package com.sdk.foddy.ui.bottom

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import com.sdk.foddy.MainActivity
import com.sdk.foddy.di.DatabaseModule
import com.sdk.foddy.di.NetworkModule
import com.sdk.foddy.ui.bottom.recipes.RecipesScreen
import com.sdk.foddy.ui.theme.FooddyTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(DatabaseModule::class, NetworkModule::class)
class RecipeScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navHostController = rememberNavController()
            FooddyTheme(darkTheme = true) {
                RecipesScreen(navHostController = navHostController)
            }
        }
    }

    @Test
    fun testRecipeScreen_lazyColumnIsDisplayed() {
        composeRule.onNodeWithTag("RecipeScreenLazyColumn").assertIsDisplayed()
        composeRule.onNodeWithTag("RecipeBackground").assertIsDisplayed()
    }

    @Test
    fun fabClicked_openBottomSheetDialog() {
        composeRule.onNodeWithContentDescription("MealFab").performClick()
        composeRule.onNodeWithContentDescription("SheetContent").assertIsDisplayed()
    }

    @Test
    fun applyBtnClicked_CheckLoading() {
        composeRule.onNodeWithContentDescription("ApplyBtn").performClick()
        composeRule.onNodeWithContentDescription("SheetContent").assertIsNotDisplayed()
        composeRule.onNodeWithTag("Loading").assertIsDisplayed()
    }
}