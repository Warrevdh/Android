package com.example.boredapp.fake

import com.example.boredapp.network.ActivityApiService
import com.example.boredapp.network.ApiActivity

class FakeActivityApiService : ActivityApiService {
    override suspend fun getActivity(): ApiActivity {
        TODO("Not yet implemented")
    }

    override suspend fun getActivityByType(type: String): ApiActivity {
        TODO("Not yet implemented")
    }

    override suspend fun getActivityByParticipants(participants: Int): ApiActivity {
        TODO("Not yet implemented")
    }

    override suspend fun getActivityByPrice(price: Float): ApiActivity {
        TODO("Not yet implemented")
    }

    override suspend fun getActivityByAccessibility(accessibility: Float): ApiActivity {
        TODO("Not yet implemented")
    }

    override suspend fun getActivityByPriceRange(
        minprice: Float,
        maxprice: Float,
    ): ApiActivity {
        TODO("Not yet implemented")
    }

    override suspend fun getActivityByAccessibilityRange(
        minaccessibility: Float,
        maxaccessibility: Float,
    ): ApiActivity {
        TODO("Not yet implemented")
    }
}
