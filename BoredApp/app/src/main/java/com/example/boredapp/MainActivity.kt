package com.example.boredapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.example.boredapp.ui.BoredApp
import com.example.boredapp.ui.navigation.NavigationType
import com.example.boredapp.ui.theme.BoredAppTheme

/**
 * The [MainActivity] class represents the main activity of the Bored app.
 *
 * It extends [ComponentActivity] and serves as the entry point for the Android application.
 * The activity sets up the user interface based on the window size classification.
 */
class MainActivity : ComponentActivity() {
    /**
     * Called when the activity is first created. It sets up the UI using [setContent] and
     * chooses the appropriate UI layout based on the window size classification.
     *
     * @param savedInstanceState The saved instance state, if any.
     */
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoredAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    // Calculate the window size class using the experimental API
                    val windowSize = calculateWindowSizeClass(activity = this)

                    // Choose the appropriate UI layout based on the window size classification
                    when (windowSize.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> {
                            BoredApp(navigationType = NavigationType.BOTTOM_NAVIGATION)
                        }
                        WindowWidthSizeClass.Medium -> {
                            BoredApp(navigationType = NavigationType.NAVIGATION_RAIL)
                        }
                        WindowWidthSizeClass.Expanded -> {
                            BoredApp(navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER)
                        }
                        else -> {
                            BoredApp(navigationType = NavigationType.BOTTOM_NAVIGATION)
                        }
                    }
                }
            }
        }
    }
}
