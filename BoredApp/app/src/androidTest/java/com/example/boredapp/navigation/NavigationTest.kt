package com.example.boredapp.navigation

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.boredapp.R
import com.example.boredapp.ui.BoredApp
import com.example.boredapp.ui.Destinations
import com.example.boredapp.ui.navigation.NavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            navController =
                TestNavHostController(LocalContext.current).apply {
                    navigatorProvider.addNavigator(ComposeNavigator())
                }
            BoredApp(navigationType = NavigationType.BOTTOM_NAVIGATION, navController = navController)
        }
    }

    @Test
    fun boredAppNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Destinations.Home.name)
        composeTestRule.onNodeWithStringId(R.string.app_name)
    }

    @Test
    fun boredAppNavHost_clickCreate_navigatesToCreateScreen() {
        composeTestRule.onNodeWithStringId(R.string.create)
            .performClick()
        navController.assertCurrentRouteName(Destinations.Create.name)
    }

    @Test
    fun boredAppNavHost_clickProfile_navigatesToProfileScreen() {
        composeTestRule.onNodeWithStringId(R.string.profile)
            .performClick()
        navController.assertCurrentRouteName(Destinations.Profile.name)
    }
}
