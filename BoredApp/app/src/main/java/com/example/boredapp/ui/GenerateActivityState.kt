package com.example.boredapp.ui

import com.example.boredapp.model.Activity

class GenerateActivityState(
    val activity: Activity = Activity(
        activity = "",
        type = "",
        participants = 0,
        price = 0.0,
        link = "",
        key = "",
        accessibility = 0.0,
    ),
)

sealed interface ActivityApiState {
    object Waiting : ActivityApiState
    object Error : ActivityApiState
    object Loading : ActivityApiState
    data class Success(val activity: Activity) : ActivityApiState
}
