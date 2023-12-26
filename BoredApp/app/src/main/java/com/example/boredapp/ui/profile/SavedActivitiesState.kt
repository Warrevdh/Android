package com.example.boredapp.ui.profile

import com.example.boredapp.model.Activity

data class SavedActivitiesState(
    val activities: List<Activity> = emptyList(),
)

sealed interface ActivityListState {
    object Empty : ActivityListState
    object Loading : ActivityListState
    object Success : ActivityListState
    object Error : ActivityListState
}
