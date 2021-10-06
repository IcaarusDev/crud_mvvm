package com.icaarusdev.crud

import android.app.Application
import com.icaarusdev.crud.base.AppDataBase
import com.icaarusdev.crud.feature.data.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SampleApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDataBase.getDataBase(this, applicationScope) }
    val repository by lazy { TaskRepository(database.taskDao()) }

    companion object {
        lateinit var instance: SampleApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}