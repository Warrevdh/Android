package com.example.boredapp.ui

import com.example.boredapp.model.Activity

data class SavedActivitiesState(
    val activities: List<Activity> = listOf(),
    val savedActivity: Activity = Activity(
        activity = "",
        type = "",
        participants = 0,
        price = 0.0,
        link = "",
        key = "",
        accessibility = 0.0,
    )
)
