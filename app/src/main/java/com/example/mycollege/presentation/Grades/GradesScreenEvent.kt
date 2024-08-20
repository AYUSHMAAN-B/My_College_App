package com.example.mycollege.presentation.Grades

sealed class GradesScreenEvent
{
    data class OnNameChange(val name: String) : GradesScreenEvent()
    data class OnCreditsChange(val credits: Int) : GradesScreenEvent()
    data class OnGradeChange(val grade: Int) : GradesScreenEvent()
    data class OnSemChange(val sem: Int) : GradesScreenEvent()
    data class DeleteGrade(val id : Int) : GradesScreenEvent()
    data class OnGradeIdChanged(val id : Int?) : GradesScreenEvent()
    data object SaveGrade : GradesScreenEvent()
}