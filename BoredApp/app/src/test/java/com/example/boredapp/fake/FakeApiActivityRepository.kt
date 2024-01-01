package com.example.boredapp.fake

import com.example.boredapp.data.ActivityRepository
import com.example.boredapp.model.Activity
import com.example.boredapp.network.ApiActivity
import kotlinx.coroutines.flow.Flow

class FakeApiActivityRepository : ActivityRepository {
    override suspend fun insertActivity(activity: Activity) {
    }

    override fun getAllActivities(): Flow<List<Activity>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteActivity(activity: Activity) {
    }

    override suspend fun deleteAllActivities() {
    }

    override suspend fun generateActivity(): ApiActivity {
        return FakeDataSource.activities[0]
    }

    override suspend fun getActivityByType(type: String): ApiActivity {
        for (activity in FakeDataSource.activities) {
            if (activity.type == type) {
                return activity
            }
        }
        return FakeDataSource.activities[0]
    }

    override suspend fun getActivityByParticipants(participants: Int): ApiActivity {
        for (activity in FakeDataSource.activities) {
            if (activity.participants == participants) {
                return activity
            }
        }
        return FakeDataSource.activities[0]
    }

    override suspend fun getActivityByPrice(price: Float): ApiActivity {
        for (activity in FakeDataSource.activities) {
            if (activity.price.toFloat() == price) {
                return activity
            }
        }
        return FakeDataSource.activities[0]
    }

    override suspend fun getActivityByAccessibility(accessibility: Float): ApiActivity {
        for (activity in FakeDataSource.activities) {
            if (activity.accessibility.toFloat() == accessibility) {
                return activity
            }
        }
        return FakeDataSource.activities[0]
    }

    override suspend fun getActivityByPriceRange(
        minPrice: Float,
        maxPrice: Float,
    ): ApiActivity {
        for (activity in FakeDataSource.activities) {
            if (activity.price in minPrice..maxPrice) {
                return activity
            }
        }
        return FakeDataSource.activities[0]
    }

    override suspend fun getActivityByAccessibilityRange(
        minAccessibility: Float,
        maxAccessibility: Float,
    ): ApiActivity {
        for (activity in FakeDataSource.activities) {
            if (activity.accessibility in minAccessibility..maxAccessibility) {
                return activity
            }
        }
        return FakeDataSource.activities[0]
    }
}
