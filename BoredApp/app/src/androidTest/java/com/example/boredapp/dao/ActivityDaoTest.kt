package com.example.boredapp.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.boredapp.data.database.ActivityDao
import com.example.boredapp.data.database.ActivityDatabase
import com.example.boredapp.data.database.asDbActivity
import com.example.boredapp.data.database.asDomainActivity
import com.example.boredapp.model.Activity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ActivityDaoTest {
    private lateinit var activityDao: ActivityDao
    private lateinit var activityDb: ActivityDatabase

    private var activity1 =
        Activity(
            1,
            "Test activity 1",
            "Test type 1",
            1,
            0.8,
            "link1",
            "key1",
            0.5,
        )

    private var activity2 =
        Activity(
            2,
            "Test activity 2",
            "Test type 2",
            2,
            0.9,
            "link2",
            "key2",
            0.6,
        )

    private suspend fun insertOneActivityToDb() {
        activityDao.insertActivity(activity1.asDbActivity())
    }

    private suspend fun insertTwoActivitiesToDb() {
        activityDao.insertActivity(activity1.asDbActivity())
        activityDao.insertActivity(activity2.asDbActivity())
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()

        activityDb =
            Room.inMemoryDatabaseBuilder(context, ActivityDatabase::class.java)
                .allowMainThreadQueries()
                .build()

        activityDao = activityDb.activityDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        activityDb.close()
    }

    @Test
    @Throws(IOException::class)
    fun daoInsert_insertActivityIntoDb() =
        runBlocking {
            insertOneActivityToDb()
            val activities = activityDao.getAllActivities().first()
            assertEquals(activities[0].asDomainActivity(), activity1)
        }

    @Test
    @Throws(IOException::class)
    fun daoGetAllActivities_getAllActivitiesFromDb() =
        runBlocking {
            insertTwoActivitiesToDb()
            val activities = activityDao.getAllActivities().first()
            assertEquals(activities[0].asDomainActivity(), activity1)
            assertEquals(activities[1].asDomainActivity(), activity2)
        }

    @Test
    @Throws(IOException::class)
    fun daoDeleteActivity_deleteActivityFromDb() =
        runBlocking {
            insertTwoActivitiesToDb()
            activityDao.deleteActivity(activity1.asDbActivity())
            val activities = activityDao.getAllActivities().first()
            assertEquals(activities[0].asDomainActivity(), activity2)
        }

    @Test
    @Throws(IOException::class)
    fun daoDeleteAllActivities_deleteAllActivitiesFromDb() =
        runBlocking {
            insertTwoActivitiesToDb()
            activityDao.deleteAllActivities()
            val activities = activityDao.getAllActivities().first()
            assertEquals(activities.size, 0)
        }
}
