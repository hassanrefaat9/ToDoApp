package com.example.todoapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("select * from task_table")
    fun getTasks():Flow<List<Task>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete (task: Task)
}