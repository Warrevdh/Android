package com.example.boredapp.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.example.boredapp.ui.navigation.NavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CreateScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            BoredApp(navigationType = NavigationType.BOTTOM_NAVIGATION)
        }
        composeTestRule.onNodeWithContentDescription("Create icon", useUnmergedTree = true).performClick()
    }

    private fun getResourceString(
        @StringRes key: Int,
    ): String {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        return context.resources.getString(key)
    }

    @Test
    fun createScreen_checkTab_tabDisplayed() {
        composeTestRule.onNodeWithText("Random").assertIsDisplayed()
    }

    @Test
    fun clickActivityButton() {
        composeTestRule.onNodeWithText("Activity").performClick()
    }

    @Test
    fun clickGenerateButton() {
        composeTestRule.onNodeWithText("Generate").performClick()
    }
}
