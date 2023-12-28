package com.example.boredapp.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ActivityApiService {
    @GET("activity")
    suspend fun getActivity(): ApiActivity
    
    @GET("activity")
    suspend fun getActivityByType(@Query("type") type: String): ApiActivity
    
    @GET("activity")
    suspend fun getActivityByParticipants(@Query("participants") participants: Int): ApiActivity
    
    @GET("activity")
    suspend fun getActivityByPrice(@Query("price") price: Float): ApiActivity
    
    @GET("activity")
    suspend fun getActivityByAccessibility(@Query("accessibility") accessibility: Float): ApiActivity
    
    @GET("activity")
    suspend fun getActivityByPriceRange(@Query("minprice") minprice: Float, @Query("maxprice") maxprice: Float): ApiActivity
    
    @GET("activity")
    suspend fun getActivityByAccessibilityRange(@Query("minaccessibility") minaccessibility: Float, @Query("maxaccessibility") maxaccessibility: Float): ApiActivity
}
