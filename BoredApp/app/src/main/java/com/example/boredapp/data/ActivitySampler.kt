package com.example.boredapp.data

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
        val list = mutableListOf<Activity>()
        repeat(10) {
            for (item in sampleActivities) {
                list.add(Activity(item, if (Random.nextInt(0, 1) == 0) { "lorem ipsum dolor sit" } else "consectetur adipiscing elit"))
            }
        }
        list
    }
}
