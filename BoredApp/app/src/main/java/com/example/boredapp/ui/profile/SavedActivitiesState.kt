package com.example.boredapp.ui.profile

import com.example.boredapp.model.Activity

/**
 * Data class representing the state of saved activities.
 *
 * @param activities List of saved activities.
 */
data class SavedActivitiesState(
    val activities: List<Activity> = emptyList(),
)

/**
 * Sealed interface representing the states of the activity list.
 */
sealed interface ActivityListState {
    /**
     * Loading state indicating that the activity list is being loaded.
     */
    object Loading : ActivityListState

    /**
     * Success state indicating that the activity list has been successfully loaded.
     */
    object Success : ActivityListState

    /**
     * Error state indicating that an error occurred while loading the activity list.
     */
    object Error : ActivityListState
}
