package com.example.mycollege.presentation.ToDo

import com.example.mycollege.data.model.ToDo

data class ToDoScreenState(
    val id : Int? = null,
    val name : String = "",
    val todos : List<ToDo> = emptyList()
)
