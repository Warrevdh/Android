package com.example.boredapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.boredapp.model.Activity

/**
 * Data class representing an activity in the database.
 *
 * @param id The unique identifier for the activity.
 * @param activity The name or description of the activity.
 * @param type The type or category of the activity.
 * @param participants The number of participants required for the activity.
 * @param price The cost factor associated with the activity.
 * @param link The URL link providing more information about the activity.
 * @param key The unique key associated with the activity.
 * @param accessibility The level of accessibility for the activity.
 */
@Entity(tableName = "activity")
data class DbActivity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val activity: String,
    val type: String,
    val participants: Int,
    val price: Double,
    val link: String,
    val key: String,
    val accessibility: Double,
)

/**
 * Extension function to convert a [DbActivity] to a domain [Activity].
 *
 * @receiver The [DbActivity] object to be converted.
 * @return The corresponding [Activity] object.
 */
fun DbActivity.asDomainActivity() =
    Activity(
        id = id,
        activity = activity,
        type = type,
        participants = participants,
        price = price,
        link = link,
        key = key,
        accessibility = accessibility,
    )

/**
 * Extension function to convert a [Activity] to a database [DbActivity].
 *
 * @receiver The [Activity] object to be converted.
 * @return The corresponding [DbActivity] object.
 */
fun Activity.asDbActivity() =
    DbActivity(
        id = id,
        activity = activity,
        type = type,
        participants = participants,
        price = price,
        link = link,
        key = key,
        accessibility = accessibility,
    )

/**
 * Extension function to convert a list of [DbActivity] objects to a list of domain [Activity] objects.
 *
 * @receiver The list of [DbActivity] objects to be converted.
 * @return The corresponding list of [Activity] objects.
 */
fun List<DbActivity>.asDomainActivities() = map { it.asDomainActivity() }
