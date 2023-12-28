package com.example.boredapp
//
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.navigation.compose.ComposeNavigator
//import androidx.navigation.testing.TestNavHostController
//import org.junit.Before
//import org.junit.Rule
//
//class NavigationTest {
//    private val someTaskName: String = "some task name"
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//    lateinit var navController: TestNavHostController
//
//    @Before
//    fun setupAppNavHost() {
//        composeTestRule.setContent {
//            navController = TestNavHostController(LocalContext.current)
//            navController.navigatorProvider.addNavigator(ComposeNavigator())
//            TaskApp(navController = navController, navigationType = TaskNavigationType.BOTTOM_NAVIGATION)
//        }
//    }
//
//    @Test
//    fun verifyStartDestination() {
//        composeTestRule
//            .onNodeWithText("TaskApp")
//            .assertIsDisplayed()
//    }
//
//    @Test
//    fun navigateToAbout() {
//        composeTestRule
//            .onNodeWithContentDescription("navigate to about page")
//            .performClick()
//        composeTestRule
//            .onNodeWithText("About")
//            .assertIsDisplayed()
//    }
//
//    @Test
//    fun clickAddTask() {
//        composeTestRule
//            .onNodeWithContentDescription("Add")
//            .performClick()
//        composeTestRule
//            .onNodeWithText("taskname")
//            .assertIsDisplayed()
//        composeTestRule
//            .onNodeWithText("Save")
//            .assertIsDisplayed()
//    }
//
//    @Test
//    fun canAddTask() {
//        composeTestRule
//            .onNodeWithContentDescription("Add")
//            .performClick()
//        composeTestRule
//            .onNodeWithText("taskname")
//            .performTextInput(someTaskName)
//        composeTestRule
//            .onNodeWithText("Save")
//            .performClick()
//        composeTestRule
//            .onNodeWithText(someTaskName)
//            .assertIsDisplayed()
//    }
//}