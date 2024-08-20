package com.example.mycollege.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mycollege.data.dao.CourseDao
import com.example.mycollege.data.dao.GradesDao
import com.example.mycollege.data.dao.MarksDao
import com.example.mycollege.data.dao.TasksDao
import com.example.mycollege.data.dao.ToDoDao
import com.example.mycollege.data.model.*

@Database (
    entities = [Course::class, Grades::class, Marks::class, Task::class, ToDo::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun courseDao(): CourseDao
    abstract fun gradesDao(): GradesDao
    abstract fun marksDao(): MarksDao
    abstract fun tasksDao(): TasksDao
    abstract fun todoDao(): ToDoDao
}