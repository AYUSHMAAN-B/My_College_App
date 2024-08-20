package com.example.mycollege.presentation.Semester.AllCourses

sealed class AllCoursesScreenEvent {
    data class OnNameChange(val name: String) : AllCoursesScreenEvent()
    data class OnCreditsChange(val credits: String) : AllCoursesScreenEvent()
    data class OnTargetGradeChange(val targetGrade: String) : AllCoursesScreenEvent()
    data class OnClassroomChange(val classroom: String) : AllCoursesScreenEvent()
    data class OnInstructorChange(val instructor: String) : AllCoursesScreenEvent()
    data object AddCourse : AllCoursesScreenEvent()
}