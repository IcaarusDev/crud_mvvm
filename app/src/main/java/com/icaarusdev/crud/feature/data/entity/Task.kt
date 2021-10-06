package com.icaarusdev.crud.feature.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val state: String
)

enum class Status {
    TODO,
    PROGRESS,
    DONE
}
