package com.icaarusdev.crud.feature.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.icaarusdev.crud.feature.data.entity.Task

@Dao
interface TaskDAO {
    @Query("SELECT * FROM tasks ORDER BY name ASC")
    fun getAllTasks(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)
}