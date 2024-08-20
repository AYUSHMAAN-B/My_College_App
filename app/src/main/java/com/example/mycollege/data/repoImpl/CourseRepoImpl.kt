package com.example.mycollege.data.repoImpl

import com.example.mycollege.data.dao.CourseDao
import com.example.mycollege.data.model.Course
import com.example.mycollege.data.repo.CourseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CourseRepoImpl @Inject constructor(
    private val courseDao: CourseDao
) : CourseRepository {
    override fun getAllCourses(): Flow<List<Course>> = courseDao.getAllCourses()
    override suspend fun getCourseById(courseId: Int) : Course = courseDao.getCourseById(courseId)
    override suspend fun upsertCourse(course: Course) = courseDao.insertCourse(course)
    override suspend fun deleteCourse(id: Int) = courseDao.deleteCourse(id)
}