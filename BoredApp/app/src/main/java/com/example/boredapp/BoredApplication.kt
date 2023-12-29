package com.example.boredapp

import android.app.Application
import com.example.boredapp.data.AppContainer
import com.example.boredapp.data.DefaultContainer

/**
 * The [BoredApplication] class represents the application class for the Bored app.
 *
 * It extends the [Application] class and initializes the [AppContainer] upon application creation.
 * The [container] holds instances of dependencies and services used throughout the application.
 */
class BoredApplication : Application() {
    /**
     * The [container] property holds the instance of the [AppContainer] for managing dependencies.
     * It is initialized in the [onCreate] method.
     */
    lateinit var container: AppContainer

    /**
     * Called when the application is first created. It initializes the [container] property.
     */
    override fun onCreate() {
        super.onCreate()
        container = DefaultContainer(context = applicationContext)
    }
}
