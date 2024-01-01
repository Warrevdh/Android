package com.example.boredapp

import com.example.boredapp.data.CachingActivityRepository
import org.junit.Test

class ActivityRepositoryTest {
    @Test
    fun activityRepository_getActivity_returnsActivity() {
        val repository = CachingActivityRepository()
    }
}
