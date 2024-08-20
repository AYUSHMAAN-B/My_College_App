package com.example.mycollege.presentation.Grades

import com.example.mycollege.data.model.Grades

data class GradesScreenState(
    val grades: List<Grades> = emptyList(),
    var id : Int? = null,
    val name : String = "",
    val credits : String = "",
    val grade : String = "",
    val sem : String = "1",
)
