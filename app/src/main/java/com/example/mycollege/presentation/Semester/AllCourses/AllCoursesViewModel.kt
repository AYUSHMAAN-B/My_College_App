package com.example.mycollege.presentation.Semester.AllCourses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycollege.data.model.Course
import com.example.mycollege.data.repo.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCoursesViewModel @Inject constructor(
    private val courseRepository: CourseRepository
) : ViewModel()
{
    private val _state = MutableStateFlow(AllCoursesScreenState())
    val state: StateFlow<AllCoursesScreenState> = _state

    init {
        fetchCourses()
    }

    fun onEvent(event : AllCoursesScreenEvent) {
        when (event) {
            is AllCoursesScreenEvent.OnNameChange -> {
                _state.value = _state.value.copy(name = event.name)
            }
            is AllCoursesScreenEvent.OnCreditsChange -> {
                _state.value = _state.value.copy(credits = event.credits)
            }
            is AllCoursesScreenEvent.OnTargetGradeChange -> {
                _state.value = _state.value.copy(targetGrade = event.targetGrade)
            }
            is AllCoursesScreenEvent.OnClassroomChange -> {
                _state.value = _state.value.copy(classroom = event.classroom)
            }
            is AllCoursesScreenEvent.OnInstructorChange -> {
                _state.value = _state.value.copy(instructor = event.instructor)
            }
            is AllCoursesScreenEvent.AddCourse -> {
                viewModelScope.launch {
                    courseRepository.upsertCourse(
                        Course(
                            name = _state.value.name,
                            credits = _state.value.credits.toInt(),
                            targetGrade = _state.value.targetGrade.toInt(),
                            classroom = _state.value.classroom,
                            instructor = _state.value.instructor
                        )
                    )
                }
            }
        }
    }

    private fun fetchCourses()
    {
        viewModelScope.launch {
            courseRepository.getAllCourses().collect{
                _state.value = AllCoursesScreenState(courses = it)
            }
        }
    }
}