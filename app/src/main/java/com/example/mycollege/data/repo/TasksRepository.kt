package com.example.mycollege.data.repo

import com.example.mycollege.data.model.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository
{
    fun getAllTasksByCourseId(courseID: Int) : Flow<List<Task>>
    suspend fun getTaskById(taskID: Int) : Task
    suspend fun upsertTask(task: Task)
    suspend fun deleteTask(taskID: Int)
    fun getUpcomingTasks(courseID: Int) : Flow<List<Task>>
    fun getCompletedTasks(courseID: Int) : Flow<List<Task>>
}