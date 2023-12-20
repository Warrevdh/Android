package com.example.boredapp.ui

import androidx.lifecycle.ViewModel
import com.example.boredapp.data.ActivitySampler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SavedActivitiesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SavedActivitiesState(activities = ActivitySampler.getAll()))
    val uiState = _uiState.asStateFlow()
}
