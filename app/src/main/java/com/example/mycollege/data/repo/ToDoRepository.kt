package com.example.mycollege.data.repo

import com.example.mycollege.data.model.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository
{
    fun getAllToDos(): Flow<List<ToDo>>
    suspend fun getToDoById(id : Int): ToDo
    suspend fun insertToDo(todo : ToDo)
    suspend fun deleteToDo(id : Int)
}