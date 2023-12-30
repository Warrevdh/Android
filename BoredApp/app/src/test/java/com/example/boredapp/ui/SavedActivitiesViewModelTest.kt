package com.example.boredapp.ui

import com.example.boredapp.fake.FakeApiActivityRepository
import com.example.boredapp.ui.profile.SavedActivitiesViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class SavedActivitiesViewModelTest {
    private val viewModel = SavedActivitiesViewModel(FakeApiActivityRepository())

    @Test
    fun viewModelStartsWithEmptyList() {
        assertEquals("", viewModel.savedActivityList.value)
    }
}
