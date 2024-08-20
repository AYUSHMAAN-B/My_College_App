package com.example.mycollege.data.repo

import com.example.mycollege.data.model.Grades
import kotlinx.coroutines.flow.Flow

interface GradesRepository
{
    fun getAllGrades(): Flow<List<Grades>>
    suspend fun getGradeById(gradesID: Int) : Grades
    suspend fun upsertGrade(grade: Grades)
    suspend fun deleteGrade(id: Int)
}