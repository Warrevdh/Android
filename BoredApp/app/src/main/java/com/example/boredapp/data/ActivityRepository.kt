package com.example.boredapp.data

import com.example.boredapp.data.database.ActivityDao
import com.example.boredapp.data.database.asDbActivity
import com.example.boredapp.data.database.asDomainActivities
import com.example.boredapp.model.Activity
import com.example.boredapp.network.ActivityApiService
import com.example.boredapp.network.ApiActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ActivityRepository {
    suspend fun insertActivity(activity: Activity)
    fun getAllActivities(): Flow<List<Activity>>
    suspend fun generateActivity(): ApiActivity
}

class CachingActivityRepository(
    private val activityDao: ActivityDao,
    private val activityApiService: ActivityApiService,
) : ActivityRepository {
    override suspend fun insertActivity(activity: Activity) {
        activityDao.insertActivity(activity.asDbActivity())
    }

    override fun getAllActivities(): Flow<List<Activity>> {
        return activityDao.getAllActivities().map {
            it.asDomainActivities()
        }
    }
    
    override suspend fun generateActivity(): ApiActivity {
        return activityApiService.getActivity()
    }
}
