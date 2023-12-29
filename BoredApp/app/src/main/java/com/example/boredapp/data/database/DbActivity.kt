package com.example.boredapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.boredapp.model.Activity

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

fun List<DbActivity>.asDomainActivities() = map { it.asDomainActivity() }
