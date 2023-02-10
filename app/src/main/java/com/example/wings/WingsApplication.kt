package com.example.wings

import android.app.Application
import com.example.wings.data.AppContainer
import com.example.wings.data.AppDataContainer

class WingsApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
