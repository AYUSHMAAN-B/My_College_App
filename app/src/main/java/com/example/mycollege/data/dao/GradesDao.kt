package com.example.mycollege.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.mycollege.data.model.Grades
import kotlinx.coroutines.flow.Flow

@Dao
interface GradesDao
{
    @Upsert
    suspend fun upsertGrade(grade: Grades)

    @Query("DELETE FROM grades WHERE id = :id")
    suspend fun deleteGrade(id: Int)

    @Query("SELECT * FROM grades")
    fun getAllGrades(): Flow<List<Grades>>

    @Query("SELECT * FROM grades WHERE id = :id")
    suspend fun getGradeById(id: Int): Grades
}