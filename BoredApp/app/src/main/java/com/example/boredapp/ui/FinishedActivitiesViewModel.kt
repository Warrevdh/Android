package com.example.boredapp.ui

import androidx.lifecycle.ViewModel
import com.example.boredapp.data.ActivitySampler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FinishedActivitiesViewModel : ViewModel() {
    private val _activityUiState = MutableStateFlow(FinishedActivitiesState(activities = ActivitySampler.getAll()))
    val activityUiState = _activityUiState.asStateFlow()
}
