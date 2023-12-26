package com.example.boredapp.network

import com.example.boredapp.model.Activity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ApiActivity(
    val id: Int = 0,
    val activity: String,
    val type: String = "",
    val participants: Int = 0,
    val price: Double = 0.0,
    val link: String = "",
    val key: String = "",
    val accessibility: Double = 0.0,
)

fun ApiActivity.asDomainObject(): Activity {
    return this.let { apiActivity ->
        Activity(
            id = apiActivity.id,
            activity = apiActivity.activity,
            type = apiActivity.type,
            participants = apiActivity.participants,
            price = apiActivity.price,
            link = apiActivity.link,
            key = apiActivity.key,
            accessibility = apiActivity.accessibility,
        )
    }
}

fun Flow<ApiActivity>.asDomainObject(): Flow<Activity> {
    return this.map {
        it.asDomainObject()
    }
}
