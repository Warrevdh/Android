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

class ProfileScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            BoredApp(navigationType = NavigationType.BOTTOM_NAVIGATION)
        }
        composeTestRule.onNodeWithContentDescription("Profile icon", useUnmergedTree = true).performClick()
    }

    private fun getResourceString(
        @StringRes key: Int,
    ): String {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        return context.resources.getString(key)
    }

    @Test
    fun profileScreen_profileScreenTitle_profileScreenTitleProfile() {
        composeTestRule.onNodeWithTag("screenTitle").assertIsDisplayed()
    }

    @Test
    fun profileScreen_navigateBack_navigatingBackHome() {
        composeTestRule.onNodeWithContentDescription("Navigate Back", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithText(getResourceString(NavOptions.Home.title)).assertIsDisplayed()
    }
}
