package com.example.mycollege.data.repo

import com.example.mycollege.data.model.Marks
import kotlinx.coroutines.flow.Flow

interface MarksRepository
{
    suspend fun upsertMarks(marks: Marks)
    suspend fun deleteMark(id: Int)
    fun getAllMarksByCourseId(courseID: Int) : Flow<List<Marks>>
    suspend fun getMarksByCourseIdAndId(courseID: Int, id: Int) : Marks
}