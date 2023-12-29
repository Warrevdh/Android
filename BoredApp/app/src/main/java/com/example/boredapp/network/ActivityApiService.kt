package com.example.boredapp.network

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface defining API endpoints for retrieving activities.
 */
interface ActivityApiService {
    /**
     * Retrieves a random activity from the external API.
     *
     * @return The retrieved [ApiActivity] object.
     */
    @GET("activity")
    suspend fun getActivity(): ApiActivity

    /**
     * Retrieves an activity from the external API based on its type.
     *
     * @param type The type or category of the activity.
     * @return The retrieved [ApiActivity] object.
     */
    @GET("activity")
    suspend fun getActivityByType(
        @Query("type") type: String,
    ): ApiActivity

    /**
     * Retrieves an activity from the external API based on the number of participants.
     *
     * @param participants The number of participants required for the activity.
     * @return The retrieved [ApiActivity] object.
     */
    @GET("activity")
    suspend fun getActivityByParticipants(
        @Query("participants") participants: Int,
    ): ApiActivity

    /**
     * Retrieves an activity from the external API based on its price.
     *
     * @param price The cost factor associated with the activity.
     * @return The retrieved [ApiActivity] object.
     */
    @GET("activity")
    suspend fun getActivityByPrice(
        @Query("price") price: Float,
    ): ApiActivity

    /**
     * Retrieves an activity from the external API based on its accessibility level.
     *
     * @param accessibility The level of accessibility for the activity.
     * @return The retrieved [ApiActivity] object.
     */
    @GET("activity")
    suspend fun getActivityByAccessibility(
        @Query("accessibility") accessibility: Float,
    ): ApiActivity

    /**
     * Retrieves an activity from the external API based on a price range.
     *
     * @param minprice The minimum price of the activity.
     * @param maxprice The maximum price of the activity.
     * @return The retrieved [ApiActivity] object.
     */
    @GET("activity")
    suspend fun getActivityByPriceRange(
        @Query("minprice") minprice: Float,
        @Query("maxprice") maxprice: Float,
    ): ApiActivity

    /**
     * Retrieves an activity from the external API based on an accessibility range.
     *
     * @param minaccessibility The minimum accessibility level of the activity.
     * @param maxaccessibility The maximum accessibility level of the activity.
     * @return The retrieved [ApiActivity] object.
     */
    @GET("activity")
    suspend fun getActivityByAccessibilityRange(
        @Query("minaccessibility") minaccessibility: Float,
        @Query("maxaccessibility") maxaccessibility: Float,
    ): ApiActivity
}
