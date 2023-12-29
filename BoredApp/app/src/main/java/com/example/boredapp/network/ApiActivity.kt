package com.example.boredapp.network

import com.example.boredapp.model.Activity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

/**
 * Data class representing an activity retrieved from an external API.
 *
 * @param id The unique identifier for the activity. Default is 0 if not provided.
 * @param activity The name or description of the activity.
 * @param type The type or category of the activity. Default is an empty string if not provided.
 * @param participants The number of participants required for the activity. Default is 0 if not provided.
 * @param price The cost factor associated with the activity. Default is 0.0 if not provided.
 * @param link The URL link providing more information about the activity. Default is an empty string if not provided.
 * @param key The unique key associated with the activity. Default is an empty string if not provided.
 * @param accessibility The level of accessibility for the activity. Default is 0.0 if not provided.
 */
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

/**
 * Extension function to convert an [ApiActivity] to a domain [Activity] object.
 *
 * @receiver The [ApiActivity] object to be converted.
 * @return The corresponding [Activity] object.
 */
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

/**
 * Extension function to convert a [Flow] of [ApiActivity] objects to a [Flow] of domain [Activity] objects.
 *
 * @receiver The [Flow] of [ApiActivity] objects to be converted.
 * @return The corresponding [Flow] of [Activity] objects.
 */
fun Flow<ApiActivity>.asDomainObject(): Flow<Activity> {
    return this.map {
        it.asDomainObject()
    }
}
