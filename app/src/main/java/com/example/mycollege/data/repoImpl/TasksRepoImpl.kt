package com.example.mycollege.data.repoImpl

import com.example.mycollege.data.dao.TasksDao
import com.example.mycollege.data.model.Task
import com.example.mycollege.data.repo.TasksRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TasksRepoImpl @Inject constructor(
    private val taskDao: TasksDao
) : TasksRepository
{
    override fun getAllTasksByCourseId(courseID: Int) = taskDao.getAllTasksByCourseId(courseID)
    override suspend fun getTaskById(taskID: Int) = taskDao.getTaskById(taskID)
    override suspend fun upsertTask(task: Task) = taskDao.insertTask(task)
    override suspend fun deleteTask(taskID: Int) = taskDao.deleteTask(taskID)
    override fun getUpcomingTasks(courseID: Int) = taskDao.getAllTasksByCourseId(courseID).map {
            task -> task.filter { it.isCompleted.not() }
    }
    override fun getCompletedTasks(courseID: Int) = taskDao.getAllTasksByCourseId(courseID).map {
            task -> task.filter { it.isCompleted }
    }
}