package com.example.boredapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Abstract class representing the Room database for managing activities.
 *
 * This class defines the database configuration, including the entities and version.
 */
@Database(entities = [DbActivity::class], version = 1)
abstract class ActivityDatabase : RoomDatabase() {
    /**
     * Provides access to the Data Access Object (DAO) for performing CRUD operations on activities.
     *
     * @return An instance of [ActivityDao] for interacting with activity data in the database.
     */
    abstract fun activityDao(): ActivityDao

    companion object {
        /**
         * Volatile instance of the [ActivityDatabase], ensuring that changes made by one thread
         * are visible to all other threads immediately.
         */
        @Volatile
        private var INSTANCE: ActivityDatabase? = null

        /**
         * Gets the singleton instance of [ActivityDatabase].
         *
         * If an instance does not exist, it creates a new one using the Room database builder.
         *
         * @param context The application context used to create the database.
         * @return The singleton instance of [ActivityDatabase].
         */
        fun getDatabase(context: Context): ActivityDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ActivityDatabase::class.java,
                    "activity_database",
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
