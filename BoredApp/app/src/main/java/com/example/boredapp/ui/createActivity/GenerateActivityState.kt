package com.example.boredapp.ui.createActivity

import com.example.boredapp.model.Activity

/**
 * Data class representing the state for generating an activity.
 *
 * @property activity The [Activity] object representing the current state of the generated activity.
 */
data class GenerateActivityState(
    var activity: Activity =
        Activity(
            activity = "",
            type = "",
            participants = 0,
            price = 0.0,
            link = "",
            key = "",
            accessibility = 0.0,
        ),
)

/**
 * Sealed interface representing the possible states of the activity API response.
 */
sealed interface ActivityApiState {
    /**
     * Represents the state when waiting for the user.
     */
    object Waiting : ActivityApiState

    /**
     * Represents the state when an error occurs during the activity API request.
     */
    object Error : ActivityApiState

    /**
     * Represents the state when no activity is found based on the API request.
     */
    object NoActivityFound : ActivityApiState

    /**
     * Represents the state when the activity API request is in progress.
     */
    object Loading : ActivityApiState

    /**
     * Represents the state when the activity API request is successful.
     */
    object Success : ActivityApiState
}
