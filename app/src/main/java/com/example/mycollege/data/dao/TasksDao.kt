package com.example.mycollege.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.mycollege.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao
{
    @Upsert
    suspend fun insertTask(task: Task)

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deleteTask(id: Int)

    @Query("SELECT * FROM task")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskById(id: Int): Task

    @Query("SELECT * FROM task WHERE courseID = :courseId")
    fun getAllTasksByCourseId(courseId: Int): Flow<List<Task>>
}