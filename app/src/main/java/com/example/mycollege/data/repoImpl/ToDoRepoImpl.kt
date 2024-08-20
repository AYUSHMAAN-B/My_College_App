package com.example.mycollege.data.repoImpl

import com.example.mycollege.data.dao.ToDoDao
import com.example.mycollege.data.model.ToDo
import com.example.mycollege.data.repo.ToDoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToDoRepoImpl @Inject constructor(
    private val toDoDao: ToDoDao
): ToDoRepository
{
    override fun getAllToDos(): Flow<List<ToDo>> = toDoDao.getAllToDos()
    override suspend fun getToDoById(id : Int): ToDo = toDoDao.getToDoById(id)
    override suspend fun insertToDo(todo : ToDo) = toDoDao.insertToDo(todo)
    override suspend fun deleteToDo(id : Int) = toDoDao.deleteToDo(id)
}