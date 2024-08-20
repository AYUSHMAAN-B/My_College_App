package com.example.mycollege.presentation.ToDo

sealed class ToDoScreenEvent
{
    data object OnInsertToDo : ToDoScreenEvent()
    data object OnCancelToDo : ToDoScreenEvent()
    data class OnNameChange(val name: String) : ToDoScreenEvent()
    data class OnDeleteToDo(val id : Int) : ToDoScreenEvent()
}