@file:OptIn(
    ExperimentalCoroutinesApi::class,
)

package com.example.boredapp

import com.example.boredapp.data.ActivityRepository
import com.example.boredapp.fake.FakeDataSource
import com.example.boredapp.network.asDomainObject
import com.example.boredapp.ui.createActivity.ActivityApiState
import com.example.boredapp.ui.createActivity.GenerateActivityViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GenerateActivityViewModelTest {
    @get:Rule
    val testCoroutineRule = TestDispatcherRule()

    @Mock
    private lateinit var repository: ActivityRepository

    private lateinit var viewModel: GenerateActivityViewModel

    private val emptyActivity = FakeDataSource.emptyActivity.asDomainObject()
    private val activityOne = FakeDataSource.activityOne.asDomainObject()
    private val activityTwo = FakeDataSource.activityTwo.asDomainObject()
    private val activityFiveParticipants = FakeDataSource.activityFiveParticipants.asDomainObject()
    private val activityTestType = FakeDataSource.activityTestType.asDomainObject()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = GenerateActivityViewModel(repository)
    }

    @Test
    fun `activityApiState default state is Waiting`() {
        assert(viewModel.activityApiState is ActivityApiState.Waiting)
    }

    @Test
    fun `generate apiActivity`() =
        runTest {
            `when`(repository.generateActivity()).thenReturn(FakeDataSource.activityOne)
            viewModel.getApiActivity()
            assert(viewModel.activityApiState is ActivityApiState.Success)
            assertEquals(viewModel.uiState.value.activity, activityOne)
        }

    @Test
    fun `generate apiActivity error`() =
        runTest {
            `when`(repository.generateActivity()).thenThrow(RuntimeException())
            viewModel.getApiActivity()
            assert(viewModel.activityApiState is ActivityApiState.Error)
        }

    @Test
    fun `generate apiActivity no activity found error`() =
        runTest {
            `when`(repository.generateActivity()).thenThrow(NullPointerException())
            viewModel.getApiActivity()
            assert(viewModel.activityApiState is ActivityApiState.NoActivityFound)
        }

    @Test
    fun `generate apiActivityByType`() =
        runTest {
            `when`(repository.getActivityByType("test")).thenReturn(FakeDataSource.activityTestType)
            viewModel.getApiActivityByType("test")
            assert(viewModel.activityApiState is ActivityApiState.Success)
            assertEquals(viewModel.uiState.value.activity, activityTestType)
        }

    @Test
    fun `generate apiActivityByType error`() =
        runTest {
            `when`(repository.getActivityByType("test")).thenThrow(RuntimeException())
            viewModel.getApiActivityByType("test")
            assert(viewModel.activityApiState is ActivityApiState.Error)
        }

    @Test
    fun `save activity`() =
        runTest {
            `when`(repository.generateActivity()).thenReturn(FakeDataSource.activityTwo)
            `when`(repository.insertActivity(activityTwo)).thenReturn(Unit)
            viewModel.getApiActivity()
            assertEquals(viewModel.uiState.value.activity, activityTwo)
            viewModel.saveActivity()
            assert(viewModel.activityApiState is ActivityApiState.Waiting)
            assertEquals(viewModel.uiState.value.activity, emptyActivity)
        }

    @Test
    fun `save activity error`() =
        runTest {
            `when`(repository.generateActivity()).thenReturn(FakeDataSource.activityTwo)
            `when`(repository.insertActivity(activityTwo)).thenThrow(RuntimeException())
            viewModel.getApiActivity()
            assertEquals(viewModel.uiState.value.activity, activityTwo)
            viewModel.saveActivity()
            assert(viewModel.activityApiState is ActivityApiState.Error)
            assertEquals(viewModel.uiState.value.activity, activityTwo)
        }

    @Test
    fun `reset activity`() =
        runTest {
            `when`(repository.generateActivity()).thenReturn(FakeDataSource.activityTwo)
            viewModel.getApiActivity()
            assertEquals(viewModel.uiState.value.activity, activityTwo)
            viewModel.resetActivity()
            assert(viewModel.activityApiState is ActivityApiState.Waiting)
            assertEquals(viewModel.uiState.value.activity, emptyActivity)
        }

    @Test
    fun `generate apiActivityByParticipants`() =
        runTest {
            `when`(repository.getActivityByParticipants(5)).thenReturn(FakeDataSource.activityFiveParticipants)
            viewModel.getApiActivityByParticipants(5)
            assert(viewModel.activityApiState is ActivityApiState.Success)
            assertEquals(viewModel.uiState.value.activity, activityFiveParticipants)
        }

    @Test
    fun `generate apiActivityByParticipants error`() =
        runTest {
            `when`(repository.getActivityByParticipants(5)).thenThrow(RuntimeException())
            viewModel.getApiActivityByParticipants(5)
            assert(viewModel.activityApiState is ActivityApiState.Error)
        }
}
