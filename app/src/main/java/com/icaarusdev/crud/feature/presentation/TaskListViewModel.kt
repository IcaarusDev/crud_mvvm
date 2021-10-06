package com.icaarusdev.crud.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.icaarusdev.crud.feature.data.TaskRepository
import com.icaarusdev.crud.feature.data.entity.Task
import kotlinx.coroutines.launch

class TaskListViewModel(private val repository: TaskRepository) : ViewModel() {
    var allTasks: LiveData<List<Task>> = repository.getAllTasks()

    fun insert(task: Task) = viewModelScope.launch {
        repository.addTask(task)
    }

    override fun onCleared() {
        super.onCleared()
    }
}

class TaskListViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}