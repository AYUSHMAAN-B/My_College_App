package com.example.mycollege.presentation.Grades

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycollege.data.model.Grades
import com.example.mycollege.data.repo.GradesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GradesScreenViewModel @Inject constructor(
    private val gradesRepository: GradesRepository
) : ViewModel()
{
    private val _state = MutableStateFlow(GradesScreenState())
    val state : MutableStateFlow<GradesScreenState> = _state

    var credits = arrayOf(0, 0, 0, 0, 0, 0, 0, 0)
    var spi = arrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    var cpi = arrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)

    init {
        fetchGrades()
    }

    fun onEvent(event: GradesScreenEvent)
    {
        when (event) {
            is GradesScreenEvent.OnNameChange -> {
                _state.value = _state.value.copy(name = event.name)
            }
            is GradesScreenEvent.OnCreditsChange -> {
                _state.value = _state.value.copy(credits = event.credits.toString())
            }
            is GradesScreenEvent.OnGradeChange -> {
                _state.value = _state.value.copy(grade = event.grade.toString())
            }
            is GradesScreenEvent.OnSemChange -> {
                _state.value = _state.value.copy(sem = event.sem.toString())
            }
            is GradesScreenEvent.DeleteGrade -> {
                viewModelScope.launch {
                    gradesRepository.deleteGrade(event.id)
                }
            }
            is GradesScreenEvent.SaveGrade -> {
                viewModelScope.launch {
                    gradesRepository.upsertGrade(
                        Grades(
                            id = _state.value.id,
                            name = _state.value.name,
                            credits = _state.value.credits.toInt(),
                            grade = _state.value.grade.toInt(),
                            sem = _state.value.sem.toInt()
                        )
                    )
                    _state.value = _state.value.copy(name = "", credits = "", grade = "", sem = "")
                }
            }
            is GradesScreenEvent.OnGradeIdChanged -> {

                if( event.id == null )
                    _state.value.id = null

                viewModelScope.launch {
                    event.id?.let {
                        gradesRepository.getGradeById(it).let {
                            _state.value = _state.value.copy(
                                id = it.id,
                                name = it.name,
                                credits = it.credits.toString(),
                                grade = it.grade.toString(),
                                sem = it.sem.toString()
                            )
                        }
                    }
                }
            }
        }
    }

    private fun fetchGrades()
    {
        viewModelScope.launch {
            gradesRepository.getAllGrades().collect{
                _state.value = GradesScreenState(grades = it)
            }
        }
    }
}