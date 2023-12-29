package com.example.boredapp.ui.profile

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
import com.example.boredapp.model.Activity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * ViewModel responsible for managing saved activities and their state.
 *
 * @param activityRepository The repository providing access to activity-related operations.
 */
class SavedActivitiesViewModel(
    private val activityRepository: ActivityRepository,
) : ViewModel() {
    /**
     * State flow representing the current state of saved activities.
     */
    lateinit var savedActivityList: StateFlow<SavedActivitiesState>

    /**
     * Mutable state representing the state of the activity list.
     * Initialized to [ActivityListState.Loading].
     */
    var activityListState: ActivityListState by mutableStateOf(ActivityListState.Loading)
        private set

    init {
        getActivities()
    }

    /**
     * Clears the list of saved activities.
     */
    fun clearList() {
        try {
            viewModelScope.launch {
                activityRepository.deleteAllActivities()
                getActivities()
            }
        } catch (e: IOException) {
            activityListState = ActivityListState.Error
        }
    }

    /**
     * Deletes a specific saved activity.
     *
     * @param activity The activity to be deleted.
     */
    fun deleteActivity(activity: Activity) {
        try {
            viewModelScope.launch {
                activityRepository.deleteActivity(activity)
                getActivities()
            }
        } catch (e: IOException) {
            activityListState = ActivityListState.Error
        }
    }

    private fun getActivities() {
        activityListState = ActivityListState.Loading
        try {
            savedActivityList =
                activityRepository.getAllActivities().map {
                    SavedActivitiesState(activities = it)
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(),
                    initialValue = SavedActivitiesState(activities = emptyList()),
                )
            activityListState = ActivityListState.Success
        } catch (e: IOException) {
            activityListState = ActivityListState.Error
        }
    }

    companion object {
        private var Instance: SavedActivitiesViewModel? = null

        /**
         * Factory for creating instances of [SavedActivitiesViewModel].
         */
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    if (Instance == null) {
                        val application = (this[APPLICATION_KEY] as BoredApplication)
                        val activityRepository = application.container.activityRepository
                        Instance = SavedActivitiesViewModel(activityRepository = activityRepository)
                    }
                    Instance!!
                }
            }
    }
}
