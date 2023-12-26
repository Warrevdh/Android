package com.example.boredapp.data

import android.content.Context
import com.example.boredapp.data.database.ActivityDatabase
import com.example.boredapp.network.ActivityApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val activityRepository: ActivityRepository
}

class DefaultContainer(
    private val context: Context,
) : AppContainer {
    private val baseUrl = "https://www.boredapi.com/api/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService: ActivityApiService by lazy {
        retrofit.create(ActivityApiService::class.java)
    }

    override val activityRepository: ActivityRepository by lazy {
        CachingActivityRepository(
            ActivityDatabase.getDatabase(context).activityDao(),
            retrofitService,
        )
    }
}
