package com.example.grades

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grades.Data.Course
import com.example.grades.Data.CourseRepository
import com.example.grades.Graph.courseRepository
import com.example.grades.ui.theme.GradesTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel ( private val courseRepository : CourseRepository = Graph.courseRepository ) : ViewModel()
{
    private val _currentScreen : MutableState<Screens> = mutableStateOf(Screens.Home)
    val currentScreen : MutableState<Screens> = _currentScreen

    fun setScreen(screen : Screens)
    {
        _currentScreen.value = screen
    }

    lateinit var getAllCourses : Flow<List<Course>>

    init {
        viewModelScope.launch {
            getAllCourses = courseRepository.getAllCourses()
        }
    }

    fun addCourse(course : Course)
    {
        viewModelScope.launch {
            courseRepository.addCourse(course)
        }
    }

    fun deleteCourse(id : Int) {
        viewModelScope.launch {
            courseRepository.deleteCourse(id)
        }
    }

    fun updateCourse(course : Course)
    {
        viewModelScope.launch {
            courseRepository.updateCourse(course)
        }
    }

    var credits = arrayOf(0, 0, 0, 0, 0, 0, 0, 0)
    var spi = arrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    var cpi = arrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
}

