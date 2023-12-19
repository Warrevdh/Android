package com.example.boredapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boredapp.data.ActivitySampler
import com.example.boredapp.network.ActivityApi.retrofitService
import com.example.boredapp.network.asDomainObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ActivityViewModel : ViewModel() {
    private val _activityUiState = MutableStateFlow(ActivityUiState(activities = ActivitySampler.getAll()))
    val activityUiState = _activityUiState.asStateFlow()
    
    var activityApiState: ActivityApiState by mutableStateOf(ActivityApiState.Loading)
        private set
    
    init {
        getApiActivity()
    }

    private fun getApiActivity() {
        viewModelScope.launch {
            try {
                val result = retrofitService.getActivity()
                activityApiState = ActivityApiState.Success(result.asDomainObject())
                println("Activity: $result")
            } catch (e: Exception) {
                activityApiState = ActivityApiState.Error
                println("Error: $e")
            }
        }
    }
}
