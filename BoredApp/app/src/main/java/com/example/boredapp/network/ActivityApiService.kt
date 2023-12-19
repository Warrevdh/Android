package com.example.boredapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://www.boredapi.com/api/"
private val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(
//        Json.asConverterFactory("application/json".toMediaType()),
        GsonConverterFactory.create(),
    )
    .baseUrl(BASE_URL)
    .build()

object ActivityApi {
    val retrofitService: ActivityApiService by lazy {
        retrofit.create(ActivityApiService::class.java)
    }
}

interface ActivityApiService {
    @GET("activity")
    suspend fun getActivity(): ApiActivity
}
