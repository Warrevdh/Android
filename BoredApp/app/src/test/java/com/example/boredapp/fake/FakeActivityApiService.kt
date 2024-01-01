package com.example.boredapp.fake

import com.example.boredapp.network.ActivityApiService
import com.example.boredapp.network.ApiActivity

class FakeActivityApiService : ActivityApiService {
    override suspend fun getActivity(): ApiActivity {
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
        minprice: Float,
        maxprice: Float,
    ): ApiActivity {
        for (activity in FakeDataSource.activities) {
            if (activity.price in minprice..maxprice) {
                return activity
            }
        }
        return FakeDataSource.activities[0]
    }

    override suspend fun getActivityByAccessibilityRange(
        minaccessibility: Float,
        maxaccessibility: Float,
    ): ApiActivity {
        for (activity in FakeDataSource.activities) {
            if (activity.accessibility in minaccessibility..maxaccessibility) {
                return activity
            }
        }
        return FakeDataSource.activities[0]
    }
}
