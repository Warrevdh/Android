package com.example.boredapp.data

import android.util.Log
import com.example.boredapp.model.Activity
import kotlin.random.Random

object ActivitySampler {
    val sampleActivities = mutableListOf(
        "Go for a walk",
        "Read a book",
        "Learn a new language",
        "Learn a new skill",
    )
    val getAll: () -> MutableList<Activity> = {
        Log.i("ActivitySampler", "getAll:")
        val list = mutableListOf<Activity>()
        for (item in sampleActivities) {
            list.add(
                Activity(
                    activity = item,
                    type = if (Random.nextInt(0, 1) == 0) { "lorem ipsum dolor sit" } else "consectetur adipiscing elit",
                    participants = Random.nextInt(1, 10),
                    price = Random.nextDouble(0.0, 1.0),
                    link = "https://www.google.com",
                    key = "1234567890",
                    accessibility = Random.nextDouble(0.0, 1.0),
                ),
            )
        }
        Log.i("ActivitySampler", "getAll: $list")
        list
    }
}
