package com.example.boredapp.ui.createActivity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.boredapp.BoredApplication
import com.example.boredapp.data.ActivityRepository
import com.example.boredapp.network.asDomainObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException

class GenerateActivityViewModel(
    private val activityRepository: ActivityRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(GenerateActivityState())
    val uiState: StateFlow<GenerateActivityState> = _uiState.asStateFlow()
    
    var activityApiState: ActivityApiState by mutableStateOf(ActivityApiState.Waiting)
        private set
    
    var saveActivityState: SaveActivityState by mutableStateOf(SaveActivityState.Loading)
        private set

    fun generateActivity() {
        getApiActivity()
    }
    
    fun resetActivity() {
        saveActivityState = SaveActivityState.Loading
        activityApiState = ActivityApiState.Waiting
        _uiState.update { GenerateActivityState() }
    }

    fun saveActivity() {
        viewModelScope.launch {
            saveActivityState = SaveActivityState.Loading
            saveActivityState = try {
                activityRepository.insertActivity(uiState.value.activity)
                resetActivity()
                SaveActivityState.Success
            } catch (e: IOException) {
                SaveActivityState.Error
            }
        }
    }

    private fun getApiActivity() {
        viewModelScope.launch {
            try {
                activityApiState = ActivityApiState.Loading
                val result = activityRepository.generateActivity()
                _uiState.update { GenerateActivityState(result.asDomainObject()) }
                activityApiState = ActivityApiState.Success
            } catch (e: IOException) {
                activityApiState = ActivityApiState.Error
            } catch (e: SocketTimeoutException) {
                activityApiState = ActivityApiState.Error
            }
        }
    }

    companion object {
        private var Instance: GenerateActivityViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[APPLICATION_KEY] as BoredApplication)
                    val activityRepository = application.container.activityRepository
                    Instance = GenerateActivityViewModel(activityRepository = activityRepository)
                }
                Instance!!
            }
        }
    }
}
