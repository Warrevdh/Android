package com.example.boredapp.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.example.boredapp.ui.navigation.NavOptions
import com.example.boredapp.ui.navigation.NavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BoredAppTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            BoredApp(navigationType = NavigationType.BOTTOM_NAVIGATION)
        }
    }

    private fun getResourceString(
        @StringRes key: Int,
    ): String {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        return context.resources.getString(key)
    }

    @Test
    fun homeNavButton() {
        composeTestRule.onNodeWithContentDescription("Home icon", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun createNavButton() {
        composeTestRule.onNodeWithContentDescription("Create icon", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun profileNavButton() {
        composeTestRule.onNodeWithContentDescription("Profile icon", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun currentScreenTitle() {
        composeTestRule.onNodeWithText(getResourceString(NavOptions.Home.title)).assertIsDisplayed()
    }

    @Test
    fun clickOnCreateNavButton() {
        composeTestRule.onNodeWithContentDescription("Create icon", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithContentDescription("Navigate Back").assertIsDisplayed()
    }

    @Test
    fun clickOnProfileNavButton() {
        composeTestRule.onNodeWithContentDescription("Profile icon", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithContentDescription("Navigate Back").assertIsDisplayed()
    }
}
