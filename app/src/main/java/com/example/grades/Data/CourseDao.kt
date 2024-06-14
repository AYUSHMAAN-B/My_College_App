package com.example.grades.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CourseDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addCourse(course : Course )

    @Query("DELETE FROM `course_table` WHERE `id`=:id")
    abstract suspend fun deleteCourse(id : Int)

    @Update
    abstract suspend fun updateCourse(course : Course)

    @Query("SELECT * FROM `course_table`")
    abstract fun getAllCourses() : Flow<List<Course>>
}