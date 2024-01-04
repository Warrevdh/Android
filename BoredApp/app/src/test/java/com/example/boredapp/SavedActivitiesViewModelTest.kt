package com.example.boredapp

import com.example.boredapp.data.ActivityRepository
import com.example.boredapp.fake.FakeDataSource
import com.example.boredapp.model.Activity
import com.example.boredapp.network.asDomainObject
import com.example.boredapp.ui.profile.ActivityListState
import com.example.boredapp.ui.profile.SavedActivitiesViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class SavedActivitiesViewModelTest {
    @get:Rule
    val testCoroutineRule = TestDispatcherRule()

    @Mock
    private lateinit var repository: ActivityRepository

    private lateinit var viewModel: SavedActivitiesViewModel

    private val activityList = FakeDataSource.activities.map { it.asDomainObject() }
    private val emptyActivityList = emptyList<Activity>()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        `when`(repository.getAllActivities()).thenReturn(flowOf(activityList))
        viewModel = SavedActivitiesViewModel(repository)
    }

    @Test
    fun `fetches saved activities on init`() =
        runTest {
            assertEquals(activityList, viewModel.savedActivityList.first().activities)
        }

    @Test
    fun `activityListState is Error when getAllActivities throws`() =
        runTest {
            `when`(repository.getAllActivities()).thenThrow(RuntimeException())
            viewModel = SavedActivitiesViewModel(repository)
            assertEquals(ActivityListState.Error, viewModel.activityListState)
        }

    @Test
    fun `clears saved activities`() =
        runTest {
            `when`(repository.deleteAllActivities()).then {
                `when`(repository.getAllActivities()).thenReturn(flowOf(emptyActivityList))
            }
            viewModel.clearList()
            assertEquals(emptyActivityList, viewModel.savedActivityList.first().activities)
        }

    @Test
    fun `clears saved activities when getAllActivities throws`() =
        runTest {
            `when`(repository.deleteAllActivities()).then {
                `when`(repository.getAllActivities()).thenThrow(RuntimeException())
            }
            viewModel.clearList()
            assertEquals(ActivityListState.Error, viewModel.activityListState)
        }

    @Test
    fun `delete one activity`() =
        runTest {
            `when`(repository.deleteActivity(activityList[0])).then {
                `when`(repository.getAllActivities()).thenReturn(flowOf(activityList.drop(1)))
            }
            viewModel.deleteActivity(activityList[0])
            assertEquals(activityList.drop(1), viewModel.savedActivityList.first().activities)
        }

    @Test
    fun `delete one activity when getAllActivities throws`() =
        runTest {
            `when`(repository.deleteActivity(activityList[0])).then {
                `when`(repository.getAllActivities()).thenThrow(RuntimeException())
            }
            viewModel.deleteActivity(activityList[0])
            assertEquals(ActivityListState.Error, viewModel.activityListState)
        }
}
