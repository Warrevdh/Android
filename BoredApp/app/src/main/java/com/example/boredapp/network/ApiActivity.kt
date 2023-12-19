package com.example.boredapp.network

import com.example.boredapp.model.Activity
import kotlinx.serialization.Serializable

@Serializable
data class ApiActivity(
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
