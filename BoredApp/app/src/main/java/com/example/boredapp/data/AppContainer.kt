package com.example.boredapp.data

import android.content.Context
import com.example.boredapp.data.database.ActivityDatabase
import com.example.boredapp.network.ActivityApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Interface representing the application container that provides dependencies such as repositories.
 */
interface AppContainer {
    /**
     * Provides access to the [ActivityRepository] for managing activities.
     */
    val activityRepository: ActivityRepository
}

/**
 * Default implementation of the [AppContainer] interface.
 *
 * @param context The application context used for creating dependencies.
 */
class DefaultContainer(
    private val context: Context,
) : AppContainer {
    /**
     * Base URL for the external API providing activity data.
     */
    private val baseUrl = "https://www.boredapi.com/api/"

    /**
     * Retrofit instance for making network requests to the external API.
     */
    private val retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    /**
     * Lazy-initialized instance of [ActivityApiService] for making API calls.
     */
    private val retrofitService: ActivityApiService by lazy {
        retrofit.create(ActivityApiService::class.java)
    }

    /**
     * Lazily initializes and provides the [ActivityRepository] implementation with caching.
     */
    override val activityRepository: ActivityRepository by lazy {
        CachingActivityRepository(
            ActivityDatabase.getDatabase(context).activityDao(),
            retrofitService,
        )
    }
}
