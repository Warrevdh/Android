package com.example.boredapp.model

/**
 * Data class representing an activity.
 *
 * @property id The unique identifier for the activity. Default is 0 if not provided.
 * @property activity The name or description of the activity.
 * @property type The type or category of the activity. Default is an empty string if not provided.
 * @property participants The number of participants required for the activity. Default is 0 if not provided.
 * @property price The cost factor associated with the activity. Default is 0.0 if not provided.
 * @property link The URL link providing more information about the activity. Default is an empty string if not provided.
 * @property key The unique key associated with the activity. Default is an empty string if not provided.
 * @property accessibility The level of accessibility for the activity. Default is 0.0 if not provided.
 */
data class Activity(
    val id: Int = 0,
    val activity: String,
    val type: String = "",
    val participants: Int = 0,
    val price: Double = 0.0,
    val link: String = "",
    val key: String = "",
    val accessibility: Double = 0.0,
)
