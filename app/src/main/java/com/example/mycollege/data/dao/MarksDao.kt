package com.example.mycollege.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.mycollege.data.model.Marks
import kotlinx.coroutines.flow.Flow

@Dao
interface MarksDao
{
    @Upsert
    suspend fun insertMarks(marks: Marks)

    @Query("DELETE FROM marks WHERE id = :marksId")
    suspend fun deleteMarks(marksId: Int)

    @Query("SELECT * FROM marks WHERE id = :marksId")
    suspend fun getMarksById(marksId: Int): Marks

    @Query("SELECT * FROM marks WHERE courseID = :courseId")
    fun getAllMarksByCourseId(courseId: Int): Flow<List<Marks>>

    @Query("SELECT * FROM marks WHERE courseID = :courseId AND id = :marksId")
    suspend fun getMarksByCourseIdAndId(courseId: Int, marksId: Int): Marks
}