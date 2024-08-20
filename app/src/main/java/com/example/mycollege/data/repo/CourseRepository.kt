package com.example.mycollege.data.repo

import com.example.mycollege.data.model.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository
{
    fun getAllCourses() : Flow<List<Course>>
    suspend fun getCourseById(courseId: Int) : Course
    suspend fun upsertCourse(course: Course)
    suspend fun deleteCourse(id: Int)
}