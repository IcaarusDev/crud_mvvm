package com.icaarusdev.crud.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.icaarusdev.crud.feature.data.entity.Task
import com.icaarusdev.crud.feature.data.local.TaskDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun taskDao() : TaskDAO

    companion object{
        @Volatile
        private var instance: AppDataBase? = null

        fun getDataBase(context: Context, scope: CoroutineScope): AppDataBase  =
            instance?: synchronized(this){
                instance?: buildDataBase(context).also{
                    instance = it
                }
            }

        private fun buildDataBase(context: Context) = Room.databaseBuilder(context, AppDataBase::class.java, "tasks")
            .fallbackToDestructiveMigration()
            .build()
    }

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            instance?.let { database ->
                scope.launch {
                    val wordDao = database.taskDao()

                    // Add sample words.
                    val word = Task(0,"Hello","")
                    wordDao.insert(word)

                    val word2 = Task(0,"Hello2","")
                    wordDao.insert(word2)
                }
            }
        }
    }
}