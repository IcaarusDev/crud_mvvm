package com.icaarusdev.crud.feature.data

import androidx.lifecycle.LiveData
import com.icaarusdev.crud.feature.data.entity.Task
import com.icaarusdev.crud.feature.data.local.TaskDAO

class TaskRepository(
    private val localDataSource: TaskDAO
) {

    fun getAllTasks(): LiveData<List<Task>> = localDataSource.getAllTasks()

    suspend fun addTask(task: Task) {
        localDataSource.insert(task)
    }

}