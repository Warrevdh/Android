package com.example.boredapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for handling CRUD operations related to activities in the database.
 *
 * This interface defines methods to insert, query, update, and delete activities.
 */
@Dao
interface ActivityDao {
    /**
     * Inserts a new activity into the database. If there is a conflict with an existing
     * activity, it replaces the existing one.
     *
     * @param activity The [DbActivity] object to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: DbActivity)

    /**
     * Retrieves all activities from the database in ascending order based on their names.
     *
     * @return A [Flow] emitting a list of [DbActivity] objects representing all activities
     *         in the database.
     */
    @Query("SELECT * FROM activity ORDER BY activity ASC")
    fun getAllActivities(): Flow<List<DbActivity>>

    /**
     * Deletes a specific activity from the database.
     *
     * @param activity The [DbActivity] object to be deleted.
     */
    @Delete
    suspend fun deleteActivity(activity: DbActivity)

    /**
     * Deletes all activities from the database.
     */
    @Query("DELETE FROM activity")
    suspend fun deleteAllActivities()
}
