package com.example.boredapp.ui

import com.example.boredapp.model.Activity

data class FinishedActivitiesState(
    val activities: List<Activity> = listOf(),
)
