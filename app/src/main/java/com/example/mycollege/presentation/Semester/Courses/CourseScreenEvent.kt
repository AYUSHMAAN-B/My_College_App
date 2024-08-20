package com.example.mycollege.presentation.Semester.Courses

import com.example.mycollege.data.model.Task

sealed class CourseScreenEvent {
    data class OnCourseNameChange(val courseName: String) : CourseScreenEvent()
    data class OnCreditsChange(val credits: String) : CourseScreenEvent()
    data class OnTargetGradeChange(val targetGrade: String) : CourseScreenEvent()
    data class OnInstructorChange(val instructor: String) : CourseScreenEvent()
    data class OnClassroomChange(val classroom: String) : CourseScreenEvent()
    data object SaveCourse : CourseScreenEvent()
    data object DeleteCourse : CourseScreenEvent()

    data class OnTaskIdChange(val taskId: Int?) : CourseScreenEvent()
    data class OnTaskNameChange(val taskName: String) : CourseScreenEvent()
    data class OnDueDateChange(val dueDate: Long) : CourseScreenEvent()
    data class OnTaskStatusChange(val task: Task) : CourseScreenEvent()
    data object SaveTask : CourseScreenEvent()
    data class DeleteTask(val task : Task) : CourseScreenEvent()

    data class OnMarkIdChange(val markId : Int?) : CourseScreenEvent()
    data class OnMarkNameChange(val markName: String) : CourseScreenEvent()
    data class OnGotChange(val got: String) : CourseScreenEvent()
    data class OnOutOfChange(val outOf: String) : CourseScreenEvent()
    data class OnWeightageChange(val weightage: String) : CourseScreenEvent()
    data object SaveMarks : CourseScreenEvent()
    data object DeleteMarks : CourseScreenEvent()
}