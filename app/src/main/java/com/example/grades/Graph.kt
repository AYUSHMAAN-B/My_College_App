package com.example.grades

import android.content.Context
import androidx.room.Room
import com.example.grades.Data.CourseDatabase
import com.example.grades.Data.CourseRepository

object Graph
{
    lateinit var database: CourseDatabase

    val courseRepository by lazy {
        CourseRepository(courseDa0 = database.courseDao())
    }

    fun provide(context: Context)
    {
        database = Room.databaseBuilder(context, CourseDatabase::class.java, "course.db").build()
    }
}