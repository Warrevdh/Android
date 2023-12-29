package com.example.boredapp.data

import com.example.boredapp.data.database.ActivityDao
import com.example.boredapp.data.database.asDbActivity
import com.example.boredapp.data.database.asDomainActivities
import com.example.boredapp.model.Activity
import com.example.boredapp.network.ActivityApiService
import com.example.boredapp.network.ApiActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Repository interface for managing activities.
 *
 * This interface defines methods for inserting, retrieving, and deleting activities, as well
 * as methods for generating activities from an external API and filtering activities based on
 * various criteria.
 */
interface ActivityRepository {
    /**
     * Inserts a new activity into the repository.
     *
     * @param activity The [Activity] object to be inserted.
     */
    suspend fun insertActivity(activity: Activity)

    /**
     * Retrieves all activities from the repository as a flow of lists.
     *
     * @return A [Flow] emitting a list of [Activity] objects representing all activities
     *         in the repository.
     */
    fun getAllActivities(): Flow<List<Activity>>

    /**
     * Deletes a specific activity from the repository.
     *
     * @param activity The [Activity] object to be deleted.
     */
    suspend fun deleteActivity(activity: Activity)

    /**
     * Deletes all activities from the repository.
     */
    suspend fun deleteAllActivities()

    /**
     * Generates a new activity from an external API.
     *
     * @return The generated [ApiActivity] object.
     */
    suspend fun generateActivity(): ApiActivity

    /**
     * Retrieves an activity from the external API based on its type.
     *
     * @param type The type or category of the activity.
     * @return The retrieved [ApiActivity] object.
     */
    suspend fun getActivityByType(type: String): ApiActivity

    /**
     * Retrieves an activity from the external API based on the number of participants.
     *
     * @param participants The number of participants required for the activity.
     * @return The retrieved [ApiActivity] object.
     */
    suspend fun getActivityByParticipants(participants: Int): ApiActivity

    /**
     * Retrieves an activity from the external API based on its price.
     *
     * @param price The cost factor associated with the activity.
     * @return The retrieved [ApiActivity] object.
     */
    suspend fun getActivityByPrice(price: Float): ApiActivity

    /**
     * Retrieves an activity from the external API based on its accessibility level.
     *
     * @param accessibility The level of accessibility for the activity.
     * @return The retrieved [ApiActivity] object.
     */
    suspend fun getActivityByAccessibility(accessibility: Float): ApiActivity

    /**
     * Retrieves an activity from the external API based on a price range.
     *
     * @param minPrice The minimum price of the activity.
     * @param maxPrice The maximum price of the activity.
     * @return The retrieved [ApiActivity] object.
     */
    suspend fun getActivityByPriceRange(
        minPrice: Float,
        maxPrice: Float,
    ): ApiActivity

    /**
     * Retrieves an activity from the external API based on an accessibility range.
     *
     * @param minAccessibility The minimum accessibility level of the activity.
     * @param maxAccessibility The maximum accessibility level of the activity.
     * @return The retrieved [ApiActivity] object.
     */
    suspend fun getActivityByAccessibilityRange(
        minAccessibility: Float,
        maxAccessibility: Float,
    ): ApiActivity
}

/**
 * Implementation of the [ActivityRepository] interface that caches activities in a local database.
 *
 * @param activityDao The Data Access Object (DAO) for interacting with the local database.
 * @param activityApiService The external API service for retrieving activities.
 */
class CachingActivityRepository(
    private val activityDao: ActivityDao,
    private val activityApiService: ActivityApiService,
) : ActivityRepository {
    /**
     * Inserts a new activity into the local database.
     *
     * @param activity The [Activity] object to be inserted.
     */
    override suspend fun insertActivity(activity: Activity) {
        activityDao.insertActivity(activity.asDbActivity())
    }

    /**
     * Retrieves all activities from the local database as a flow of lists.
     *
     * @return A [Flow] emitting a list of [Activity] objects representing all activities
     *         in the local database.
     */
    override fun getAllActivities(): Flow<List<Activity>> {
        return activityDao.getAllActivities().map {
            it.asDomainActivities()
        }
    }

    /**
     * Deletes a specific activity from the local database.
     *
     * @param activity The [Activity] object to be deleted.
     */
    override suspend fun deleteActivity(activity: Activity) {
        activityDao.deleteActivity(activity.asDbActivity())
    }

    /**
     * Deletes all activities from the local database.
     */
    override suspend fun deleteAllActivities() {
        activityDao.deleteAllActivities()
    }

    /**
     * Generates a new activity from the external API.
     *
     * @return The generated [ApiActivity] object.
     */
    override suspend fun generateActivity(): ApiActivity {
        return activityApiService.getActivity()
    }

    /**
     * Retrieves an activity from the external API based on its type.
     *
     * @param type The type or category of the activity.
     * @return The retrieved [ApiActivity] object.
     */
    override suspend fun getActivityByType(type: String): ApiActivity {
        return activityApiService.getActivityByType(type)
    }

    /**
     * Retrieves an activity from the external API based on the number of participants.
     *
     * @param participants The number of participants required for the activity.
     * @return The retrieved [ApiActivity] object.
     */
    override suspend fun getActivityByParticipants(participants: Int): ApiActivity {
        return activityApiService.getActivityByParticipants(participants)
    }

    /**
     * Retrieves an activity from the external API based on its price.
     *
     * @param price The cost factor associated with the activity.
     * @return The retrieved [ApiActivity] object.
     */
    override suspend fun getActivityByPrice(price: Float): ApiActivity {
        return activityApiService.getActivityByPrice(price)
    }

    /**
     * Retrieves an activity from the external API based on its accessibility level.
     *
     * @param accessibility The level of accessibility for the activity.
     * @return The retrieved [ApiActivity] object.
     */
    override suspend fun getActivityByAccessibility(accessibility: Float): ApiActivity {
        return activityApiService.getActivityByAccessibility(accessibility)
    }

    /**
     * Retrieves an activity from the external API based on a price range.
     *
     * @param minPrice The minimum price of the activity.
     * @param maxPrice The maximum price of the activity.
     * @return The retrieved [ApiActivity] object.
     */
    override suspend fun getActivityByPriceRange(
        minPrice: Float,
        maxPrice: Float,
    ): ApiActivity {
        return activityApiService.getActivityByPriceRange(minPrice, maxPrice)
    }

    /**
     * Retrieves an activity from the external API based on an accessibility range.
     *
     * @param minAccessibility The minimum accessibility level of the activity.
     * @param maxAccessibility The maximum accessibility level of the activity.
     * @return The retrieved [ApiActivity] object.
     */
    override suspend fun getActivityByAccessibilityRange(
        minAccessibility: Float,
        maxAccessibility: Float,
    ): ApiActivity {
        return activityApiService.getActivityByAccessibilityRange(minAccessibility, maxAccessibility)
    }
}
