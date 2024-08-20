package com.example.mycollege.presentation.Semester.AllCourses

import com.example.mycollege.data.model.Course

data class AllCoursesScreenState(
    val name: String = "",
    val credits: String = "",
    val targetGrade: String = "",
    val instructor: String? = null,
    val classroom: String? = null,
    val courses: List<Course>? = emptyList()
)