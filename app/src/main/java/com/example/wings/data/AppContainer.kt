package com.example.wings.data

import android.content.Context

interface AppContainer {
    val wingsRepository: WingsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val wingsRepository: WingsRepository by lazy {
        WingsRepositoryImp(WingsDatabase.getDatabase(context).wingsDao())
    }

}