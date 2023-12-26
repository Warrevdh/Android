package com.example.boredapp.network

import retrofit2.http.GET

interface ActivityApiService {
    @GET("activity")
    suspend fun getActivity(): ApiActivity
}
