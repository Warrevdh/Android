package com.example.boredapp.ui

import com.example.boredapp.model.Activity

sealed interface ActivityApiState {
    object Error : ActivityApiState
    object Loading : ActivityApiState
    data class Success(val activity: Activity) : ActivityApiState
}