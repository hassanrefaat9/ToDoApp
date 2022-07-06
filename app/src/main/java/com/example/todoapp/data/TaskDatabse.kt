package com.example.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todoapp.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [(Task::class)], version = 1)
abstract class TaskDatabase : RoomDatabase(){

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
      private val database: Provider<TaskDatabase>,
      @ApplicationScope private val applicationScope: CoroutineScope
    ) :RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

        val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("wash the dishes", important = true, completed = true))
                dao.insert(Task("Android project", completed = true))
                dao.insert(Task("Network course", important = true))
                dao.insert(Task("Jetback compose"))
                dao.insert(Task("Dagger-Hilt"))
                dao.insert(Task("I love Android",completed = true))
            }


        }
    }
}