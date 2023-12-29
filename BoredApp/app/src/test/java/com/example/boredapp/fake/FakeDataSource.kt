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
    const val participants2 = 10
    const val price2 = 0.3
    const val link2 = "link2"
    const val key2 = "key2"
    const val accessibility2 = 0.2

    val activities =
        listOf(
            ApiActivity(
                activity = activity1,
                type = type1,
                participants = participants1,
                price = price1,
                link = link1,
                key = key1,
                accessibility = accessibility1,
            ),
            ApiActivity(
                activity = activity2,
                type = type2,
                participants = participants2,
                price = price2,
                link = link2,
                key = key2,
                accessibility = accessibility2,
            ),
        )
}
