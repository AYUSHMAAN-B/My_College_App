package com.example.mycollege.presentation.ToDo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycollege.data.model.ToDo
import com.example.mycollege.data.repo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val todoRepository: ToDoRepository
) : ViewModel()
{
    private val _state = MutableStateFlow(ToDoScreenState())
    val state: StateFlow<ToDoScreenState> = _state

    init {
        fetchToDos()
    }

    fun onEvent(event : ToDoScreenEvent)
    {
        when (event) {
            is ToDoScreenEvent.OnNameChange -> {
                _state.value = _state.value.copy(name = event.name)
            }
            is ToDoScreenEvent.OnInsertToDo -> {
                viewModelScope.launch {
                    Log.d("ToDoViewModel", "onEvent: ${_state.value.id} ${_state.value.name}")
                    todoRepository.insertToDo(
                        ToDo(
                            id = null,
                            name = _state.value.name
                        )
                    )

                    _state.value = _state.value.copy(id = null, name = "")
                }
            }
            is ToDoScreenEvent.OnDeleteToDo -> {
                viewModelScope.launch {
                    todoRepository.deleteToDo(event.id)
                }
            }
            is ToDoScreenEvent.OnCancelToDo -> {
                _state.value = ToDoScreenState()
            }
        }
    }

    private fun fetchToDos()
    {
        viewModelScope.launch {
            todoRepository.getAllToDos().collect{
                _state.value = ToDoScreenState(todos = it)
            }
        }
    }
}