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

    fun resetApiState() {
        activityApiState = ActivityApiState.Waiting
    }

    fun resetActivity() {
        _uiState.update { GenerateActivityState() }
        activityApiState = ActivityApiState.Waiting
    }

    fun saveActivity() {
        viewModelScope.launch {
            try {
                activityRepository.insertActivity(uiState.value.activity)
                resetActivity()
            } catch (e: IOException) {
                activityApiState = ActivityApiState.Error
            }
        }
    }

    fun getApiActivity() {
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
            } catch (e: NullPointerException) {
                activityApiState = ActivityApiState.NoActivityFound
            }
        }
    }

    fun getApiActivityByType(type: String) {
        viewModelScope.launch {
            try {
                activityApiState = ActivityApiState.Loading
                val result = activityRepository.getActivityByType(type)
                _uiState.update { GenerateActivityState(result.asDomainObject()) }
                activityApiState = ActivityApiState.Success
            } catch (e: IOException) {
                activityApiState = ActivityApiState.Error
            } catch (e: SocketTimeoutException) {
                activityApiState = ActivityApiState.Error
            } catch (e: NullPointerException) {
                activityApiState = ActivityApiState.NoActivityFound
            }
        }
    }

    fun getApiActivityByAccessibility(accessibility: Float) {
        viewModelScope.launch {
            try {
                activityApiState = ActivityApiState.Loading
                val result = activityRepository.getActivityByAccessibility(accessibility)
                _uiState.update { GenerateActivityState(result.asDomainObject()) }
                activityApiState = ActivityApiState.Success
            } catch (e: IOException) {
                activityApiState = ActivityApiState.Error
            } catch (e: SocketTimeoutException) {
                activityApiState = ActivityApiState.Error
            } catch (e: NullPointerException) {
                activityApiState = ActivityApiState.NoActivityFound
            }
        }
    }

    fun getApiActivityByParticipants(participants: Int) {
        viewModelScope.launch {
            try {
                activityApiState = ActivityApiState.Loading
                val result = activityRepository.getActivityByParticipants(participants)
                _uiState.update { GenerateActivityState(result.asDomainObject()) }
                activityApiState = ActivityApiState.Success
            } catch (e: IOException) {
                activityApiState = ActivityApiState.Error
            } catch (e: SocketTimeoutException) {
                activityApiState = ActivityApiState.Error
            } catch (e: NullPointerException) {
                activityApiState = ActivityApiState.NoActivityFound
            }
        }
    }

    fun getApiActivityByPrice(price: Float) {
        viewModelScope.launch {
            try {
                activityApiState = ActivityApiState.Loading
                val result = activityRepository.getActivityByPrice(price)
                _uiState.update { GenerateActivityState(result.asDomainObject()) }
                activityApiState = ActivityApiState.Success
            } catch (e: IOException) {
                activityApiState = ActivityApiState.Error
            } catch (e: SocketTimeoutException) {
                activityApiState = ActivityApiState.Error
            } catch (e: NullPointerException) {
                activityApiState = ActivityApiState.NoActivityFound
            }
        }
    }

    fun getApiActivityByPriceRange(
        minPrice: Float,
        maxPrice: Float,
    ) {
        viewModelScope.launch {
            try {
                activityApiState = ActivityApiState.Loading
                val result = activityRepository.getActivityByPriceRange(minPrice, maxPrice)
                _uiState.update { GenerateActivityState(result.asDomainObject()) }
                activityApiState = ActivityApiState.Success
            } catch (e: IOException) {
                activityApiState = ActivityApiState.Error
            } catch (e: SocketTimeoutException) {
                activityApiState = ActivityApiState.Error
            } catch (e: NullPointerException) {
                activityApiState = ActivityApiState.NoActivityFound
            }
        }
    }

    fun getApiActivityByAccessibilityRange(
        minAccessibility: Float,
        maxAccessibility: Float,
    ) {
        viewModelScope.launch {
            try {
                activityApiState = ActivityApiState.Loading
                val result = activityRepository.getActivityByAccessibilityRange(minAccessibility, maxAccessibility)
                _uiState.update { GenerateActivityState(result.asDomainObject()) }
                activityApiState = ActivityApiState.Success
            } catch (e: IOException) {
                activityApiState = ActivityApiState.Error
            } catch (e: SocketTimeoutException) {
                activityApiState = ActivityApiState.Error
            } catch (e: NullPointerException) {
                activityApiState = ActivityApiState.NoActivityFound
            }
        }
    }

    companion object {
        private var Instance: GenerateActivityViewModel? = null
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
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
