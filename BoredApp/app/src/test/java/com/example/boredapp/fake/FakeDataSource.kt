package com.example.boredapp.fake

import com.example.boredapp.network.ApiActivity

object FakeDataSource {
    const val activity1 = "title1"
    const val type1 = "type1"
    const val participants1 = 1
    const val price1 = 0.8
    const val link1 = "link1"
    const val key1 = "key1"
    const val accessibility1 = 0.7
    const val activity2 = "title2"
    const val type2 = "type2"
    const val participants2 = 2
    const val price2 = 0.9
    const val link2 = "link2"
    const val key2 = "key2"
    const val accessibility2 = 0.8
    const val activity3 = "title3"
    const val type3 = "type3"
    const val participants3 = 3
    const val price3 = 1.0
    const val link3 = "link3"
    const val key3 = "key3"
    const val accessibility3 = 0.9

    val emptyActivity =
        ApiActivity(
            activity = "",
            type = "",
            participants = 0,
            price = 0.0,
            link = "",
            key = "",
            accessibility = 0.0,
        )
    val activityOne =
        ApiActivity(
            activity = activity1,
            type = type1,
            participants = participants1,
            price = price1,
            link = link1,
            key = key1,
            accessibility = accessibility1,
        )
    val activityTwo =
        ApiActivity(
            activity = activity2,
            type = type2,
            participants = participants2,
            price = price2,
            link = link2,
            key = key2,
            accessibility = accessibility2,
        )
    val activityThree =
        ApiActivity(
            activity = activity3,
            type = type3,
            participants = participants3,
            price = price3,
            link = link3,
            key = key3,
            accessibility = accessibility3,
        )
    val activityFiveParticipants =
        ApiActivity(
            activity = activity1,
            type = type1,
            participants = 5,
            price = price1,
            link = link1,
            key = key1,
            accessibility = accessibility1,
        )
    val activityTestType =
        ApiActivity(
            activity = activity1,
            type = "test",
            participants = participants1,
            price = price1,
            link = link1,
            key = key1,
            accessibility = accessibility1,
        )

    val activities =
        listOf(
            activityOne,
            activityTwo,
            activityThree,
            activityFiveParticipants,
            activityTestType,
        )
}
