package com.example.mycollege.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.mycollege.data.model.ToDo
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao
{
    @Upsert
    suspend fun insertToDo(todo : ToDo)

    @Query("DELETE FROM todo WHERE id = :id")
    suspend fun deleteToDo(id : Int)

    @Query("SELECT * FROM todo")
    fun getAllToDos(): Flow<List<ToDo>>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getToDoById(id : Int): ToDo
}