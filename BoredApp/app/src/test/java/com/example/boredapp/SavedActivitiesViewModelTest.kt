@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.boredapp

import com.example.boredapp.fake.FakeApiActivityRepository
import com.example.boredapp.ui.profile.SavedActivitiesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class SavedActivitiesViewModelTest {
    lateinit var viewModel: SavedActivitiesViewModel

    @get:Rule
    val testDispatcher = TestDispatchersRule()

    @Before
    fun setUp() {
        viewModel = SavedActivitiesViewModel(FakeApiActivityRepository())
    }

    @Test
    fun viewModelStartsWithEmptyList() {
        assertEquals(0, viewModel.savedActivityList.value.activities.size)
    }
}

class TestDispatchersRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}
