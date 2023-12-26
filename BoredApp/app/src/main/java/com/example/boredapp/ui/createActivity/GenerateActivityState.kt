package com.example.boredapp.ui.createActivity

import com.example.boredapp.model.Activity

data class GenerateActivityState(
    var activity: Activity = Activity(
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
    object Success : ActivityApiState
}

sealed interface SaveActivityState {
    object Error : SaveActivityState
    object Loading : SaveActivityState
    object Success : SaveActivityState
}
