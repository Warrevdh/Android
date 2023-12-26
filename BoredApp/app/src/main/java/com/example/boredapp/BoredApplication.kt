package com.example.boredapp

import android.app.Application
import com.example.boredapp.data.AppContainer
import com.example.boredapp.data.DefaultContainer

class BoredApplication : Application() {
    lateinit var container: AppContainer
        
    override fun onCreate() {
        super.onCreate()
        container = DefaultContainer(context = applicationContext)
    }
}
