package com.example.boredapp.network

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service interface for fetching activities from an external API.
 */
interface ActivityApiService {
    /**
     * Fetches a random activity from the API.
     *
     * @return An [ApiActivity] object representing the fetched activity.
     */
    @GET("activity")
    suspend fun getActivity(): ApiActivity

    /**
     * Fetches activities based on the specified type.
     *
     * @param type The type of activity to filter by.
     * @return An [ApiActivity] object representing the fetched activities with the specified type.
     */
    @GET("activity")
    suspend fun getActivityByType(
        @Query("type") type: String,
    ): ApiActivity

    /**
     * Fetches activities based on the specified number of participants.
     *
     * @param participants The number of participants for the activity.
     * @return An [ApiActivity] object representing the fetched activities with the specified number of participants.
     */
    @GET("activity")
    suspend fun getActivityByParticipants(
        @Query("participants") participants: Int,
    ): ApiActivity

    /**
     * Fetches activities based on the specified price.
     *
     * @param price The price of the activity.
     * @return An [ApiActivity] object representing the fetched activities with the specified price.
     */
    @GET("activity")
    suspend fun getActivityByPrice(
        @Query("price") price: Float,
    ): ApiActivity

    /**
     * Fetches activities based on the specified accessibility level.
     *
     * @param accessibility The accessibility level of the activity.
     * @return An [ApiActivity] object representing the fetched activities with the specified accessibility level.
     */
    @GET("activity")
    suspend fun getActivityByAccessibility(
        @Query("accessibility") accessibility: Float,
    ): ApiActivity

    /**
     * Fetches activities within the specified price range.
     *
     * @param minprice The minimum price for the activity.
     * @param maxprice The maximum price for the activity.
     * @return An [ApiActivity] object representing the fetched activities within the specified price range.
     */
    @GET("activity")
    suspend fun getActivityByPriceRange(
        @Query("minprice") minprice: Float,
        @Query("maxprice") maxprice: Float,
    ): ApiActivity

    /**
     * Fetches activities within the specified accessibility range.
     *
     * @param minaccessibility The minimum accessibility level for the activity.
     * @param maxaccessibility The maximum accessibility level for the activity.
     * @return An [ApiActivity] object representing the fetched activities within the specified accessibility range.
     */
    @GET("activity")
    suspend fun getActivityByAccessibilityRange(
        @Query("minaccessibility") minaccessibility: Float,
        @Query("maxaccessibility") maxaccessibility: Float,
    ): ApiActivity
}
