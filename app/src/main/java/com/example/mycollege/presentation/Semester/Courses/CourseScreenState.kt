package com.example.mycollege.presentation.Semester.Courses

import com.example.mycollege.data.model.Marks
import com.example.mycollege.data.model.Task

data class CourseScreenState (
    val courseId: Int? = null,
    val courseName: String = "",
    val credits: String = "",
    val targetGrade: String = "",
    val instructor: String? = null,
    val classroom: String? = null,

    val upcomingTasks: List<Task> = emptyList(),
    val completedTasks: List<Task> = emptyList(),
    val taskId : Int? = null,
    val taskName : String = "",
    var dueDate : Long = 0,
    val isCompleted : Boolean = false,

    val allMarks : List<Marks> = emptyList(),
    val markId : Int? = null,
    val markName : String = "",
    val got : String = "",
    val outOf : String = "",
    val weightage : String = "",
    var total : Double = 0.0
)