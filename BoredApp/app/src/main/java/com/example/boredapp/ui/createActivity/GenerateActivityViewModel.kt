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

/**
 * ViewModel responsible for handling the generation and retrieval of activity data.
 *
 * @property activityRepository The repository providing access to activity-related data.
 */
class GenerateActivityViewModel(
    private val activityRepository: ActivityRepository,
) : ViewModel() {
    /**
     * Mutable state flow representing the UI state for generating an activity.
     */
    private val _uiState = MutableStateFlow(GenerateActivityState())

    /**
     * Immutable state flow representing the UI state for generating an activity.
     */
    val uiState: StateFlow<GenerateActivityState> = _uiState.asStateFlow()

    /**
     * Property representing the current state of the activity API response.
     */
    var activityApiState: ActivityApiState by mutableStateOf(ActivityApiState.Waiting)
        private set

    /**
     * Resets the activity API state to [ActivityApiState.Waiting].
     */
    fun resetApiState() {
        activityApiState = ActivityApiState.Waiting
    }

    /**
     * Resets the generated activity and sets the activity API state to [ActivityApiState.Waiting].
     */
    fun resetActivity() {
        _uiState.update { GenerateActivityState() }
        activityApiState = ActivityApiState.Waiting
    }

    /**
     * Saves the currently generated activity to the repository.
     * Resets the generated activity afterward.
     */
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

    // Functions for retrieving activities from the activity repository based on different criteria

    fun getApiActivity() {
        activityApiState = ActivityApiState.Loading
        viewModelScope.launch {
            activityApiState =
                try {
                    val result = activityRepository.generateActivity()
                    _uiState.update { GenerateActivityState(result.asDomainObject()) }
                    ActivityApiState.Success
                } catch (e: IOException) {
                    ActivityApiState.Error
                } catch (e: SocketTimeoutException) {
                    ActivityApiState.Error
                } catch (e: NullPointerException) {
                    ActivityApiState.NoActivityFound
                }
        }
    }

    fun getApiActivityByType(type: String) {
        viewModelScope.launch {
            activityApiState = ActivityApiState.Loading
            activityApiState =
                try {
                    val result = activityRepository.getActivityByType(type)
                    _uiState.update { GenerateActivityState(result.asDomainObject()) }
                    ActivityApiState.Success
                } catch (e: IOException) {
                    ActivityApiState.Error
                } catch (e: SocketTimeoutException) {
                    ActivityApiState.Error
                } catch (e: NullPointerException) {
                    ActivityApiState.NoActivityFound
                }
        }
    }

    fun getApiActivityByAccessibility(accessibility: Float) {
        activityApiState = ActivityApiState.Loading
        viewModelScope.launch {
            activityApiState =
                try {
                    val result = activityRepository.getActivityByAccessibility(accessibility)
                    _uiState.update { GenerateActivityState(result.asDomainObject()) }
                    ActivityApiState.Success
                } catch (e: IOException) {
                    ActivityApiState.Error
                } catch (e: SocketTimeoutException) {
                    ActivityApiState.Error
                } catch (e: NullPointerException) {
                    ActivityApiState.NoActivityFound
                }
        }
    }

    fun getApiActivityByParticipants(participants: Int) {
        activityApiState = ActivityApiState.Loading
        viewModelScope.launch {
            activityApiState =
                try {
                    val result = activityRepository.getActivityByParticipants(participants)
                    _uiState.update { GenerateActivityState(result.asDomainObject()) }
                    ActivityApiState.Success
                } catch (e: IOException) {
                    ActivityApiState.Error
                } catch (e: SocketTimeoutException) {
                    ActivityApiState.Error
                } catch (e: NullPointerException) {
                    ActivityApiState.NoActivityFound
                }
        }
    }

    fun getApiActivityByPrice(price: Float) {
        activityApiState = ActivityApiState.Loading
        viewModelScope.launch {
            activityApiState =
                try {
                    val result = activityRepository.getActivityByPrice(price)
                    _uiState.update { GenerateActivityState(result.asDomainObject()) }
                    ActivityApiState.Success
                } catch (e: IOException) {
                    ActivityApiState.Error
                } catch (e: SocketTimeoutException) {
                    ActivityApiState.Error
                } catch (e: NullPointerException) {
                    ActivityApiState.NoActivityFound
                }
        }
    }

    fun getApiActivityByPriceRange(
        minPrice: Float,
        maxPrice: Float,
    ) {
        activityApiState = ActivityApiState.Loading
        viewModelScope.launch {
            activityApiState =
                try {
                    val result = activityRepository.getActivityByPriceRange(minPrice, maxPrice)
                    _uiState.update { GenerateActivityState(result.asDomainObject()) }
                    ActivityApiState.Success
                } catch (e: IOException) {
                    ActivityApiState.Error
                } catch (e: SocketTimeoutException) {
                    ActivityApiState.Error
                } catch (e: NullPointerException) {
                    ActivityApiState.NoActivityFound
                }
        }
    }

    fun getApiActivityByAccessibilityRange(
        minAccessibility: Float,
        maxAccessibility: Float,
    ) {
        activityApiState = ActivityApiState.Loading
        viewModelScope.launch {
            activityApiState =
                try {
                    val result = activityRepository.getActivityByAccessibilityRange(minAccessibility, maxAccessibility)
                    _uiState.update { GenerateActivityState(result.asDomainObject()) }
                    ActivityApiState.Success
                } catch (e: IOException) {
                    ActivityApiState.Error
                } catch (e: SocketTimeoutException) {
                    ActivityApiState.Error
                } catch (e: NullPointerException) {
                    ActivityApiState.NoActivityFound
                }
        }
    }

    /**
     * Factory companion object for creating instances of [GenerateActivityViewModel].
     */
    companion object {
        private var Instance: GenerateActivityViewModel? = null

        /**
         * Factory for creating instances of [GenerateActivityViewModel].
         */
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
