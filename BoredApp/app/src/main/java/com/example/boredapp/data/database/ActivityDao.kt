package com.example.boredapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: DbActivity)

    @Query("SELECT * FROM activity ORDER BY activity ASC")
    fun getAllActivities(): Flow<List<DbActivity>>

    @Delete
    suspend fun deleteActivity(activity: DbActivity)

    @Query("DELETE FROM activity")
    suspend fun deleteAllActivities()
}
