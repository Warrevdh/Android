package com.example.boredapp.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boredapp.data.ActivitySampler
import com.example.boredapp.network.ActivityApi
import com.example.boredapp.network.asDomainObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class GenerateActivityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GenerateActivityState(currentActivityList = ActivitySampler.getAll()))
    val uiState: StateFlow<GenerateActivityState> = _uiState.asStateFlow()
    
    var activityApiState: ActivityApiState by mutableStateOf(ActivityApiState.Waiting)
        private set

    fun addActivity() {
        Log.i("GenerateActivityViewModel", "startAddActivity: ${_uiState.value.currentActivityList}")
        
        _uiState.update {
                currentState ->
            currentState.copy(
                currentActivityList = currentState.currentActivityList + currentState.activity,
            )
        }
        Log.i("GenerateActivityViewModel", "addActivity: ${_uiState.value.currentActivityList}")
        resetActivity()
    }
    
    fun generateActivity() {
        getApiActivity()
    }
    
    fun resetActivity() {
        activityApiState = ActivityApiState.Waiting
        _uiState.update { GenerateActivityState() }
    }
    
    private fun getApiActivity() {
        viewModelScope.launch {
            try {
                activityApiState = ActivityApiState.Loading
                val result = ActivityApi.retrofitService.getActivity()
                _uiState.update { GenerateActivityState(activity = result.asDomainObject()) }
                activityApiState = ActivityApiState.Success(result.asDomainObject())
                println("Success: $result")
            } catch (e: IOException) {
                activityApiState = ActivityApiState.Error
                println("Error: $e")
            }
        }
    }
}
