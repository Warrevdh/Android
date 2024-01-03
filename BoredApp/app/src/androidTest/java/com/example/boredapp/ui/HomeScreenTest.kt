package com.example.boredapp.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.example.boredapp.ui.navigation.NavOptions
import com.example.boredapp.ui.navigation.NavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
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
    fun homeScreen_homeIcon_homeIconDisplayed() {
        composeTestRule.onNodeWithContentDescription("Home icon", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun homeScreen_createIcon_createIconDisplayed() {
        composeTestRule.onNodeWithContentDescription("Create icon", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun homeScreen_profileIcon_profileIconDisplayed() {
        composeTestRule.onNodeWithContentDescription("Profile icon", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun homeScreen_homeScreenTitle_homeScreenTitleDisplayed() {
        composeTestRule.onNodeWithTag("screenTitle").assertIsDisplayed()
    }

    @Test
    fun homeScreen_navigateToCreateAndBack_canNavigate() {
        composeTestRule.onNodeWithContentDescription("Create icon", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithContentDescription("Navigate Back").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Navigate Back", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithText(getResourceString(NavOptions.Home.title)).assertIsDisplayed()
    }
}
