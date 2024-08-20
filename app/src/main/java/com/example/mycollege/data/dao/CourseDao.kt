package com.example.mycollege.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.mycollege.data.model.Course
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao
{
    @Upsert
    suspend fun insertCourse(course: Course)

    @Query("DELETE FROM course WHERE id = :courseId")
    suspend fun deleteCourse(courseId : Int)

    @Query("SELECT * FROM course WHERE id = :courseId")
    suspend fun getCourseById(courseId : Int) : Course

    @Query("SELECT * FROM course")
    fun getAllCourses() : Flow<List<Course>>
}