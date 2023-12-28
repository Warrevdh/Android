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
    suspend fun deleteActivity(activity: Activity)
    suspend fun deleteAllActivities()
    suspend fun generateActivity(): ApiActivity
    suspend fun getActivityByType(type: String): ApiActivity
    suspend fun getActivityByParticipants(participants: Int): ApiActivity
    suspend fun getActivityByPrice(price: Float): ApiActivity
    suspend fun getActivityByAccessibility(accessibility: Float): ApiActivity
    suspend fun getActivityByPriceRange(minPrice: Float, maxPrice: Float): ApiActivity
    suspend fun getActivityByAccessibilityRange(minAccessibility: Float, maxAccessibility: Float): ApiActivity
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
    
    override suspend fun deleteActivity(activity: Activity) {
        activityDao.deleteActivity(activity.asDbActivity())
    }
    
    override suspend fun deleteAllActivities() {
        activityDao.deleteAllActivities()
    }
    
    override suspend fun generateActivity(): ApiActivity {
        return activityApiService.getActivity()
    }
    
    override suspend fun getActivityByType(type: String): ApiActivity {
        return activityApiService.getActivityByType(type)
    }
    
    override suspend fun getActivityByParticipants(participants: Int): ApiActivity {
        return activityApiService.getActivityByParticipants(participants)
    }
    
    override suspend fun getActivityByPrice(price: Float): ApiActivity {
        return activityApiService.getActivityByPrice(price)
    }
    
    override suspend fun getActivityByAccessibility(accessibility: Float): ApiActivity {
        return activityApiService.getActivityByAccessibility(accessibility)
    }
    
    override suspend fun getActivityByPriceRange(minPrice: Float, maxPrice: Float): ApiActivity {
        return activityApiService.getActivityByPriceRange(minPrice, maxPrice)
    }
    
    override suspend fun getActivityByAccessibilityRange(minAccessibility: Float, maxAccessibility: Float): ApiActivity {
        return activityApiService.getActivityByAccessibilityRange(minAccessibility, maxAccessibility)
    }
}
