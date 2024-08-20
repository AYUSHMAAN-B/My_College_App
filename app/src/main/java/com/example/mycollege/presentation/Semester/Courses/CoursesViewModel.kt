package com.example.mycollege.presentation.Semester.Courses

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycollege.data.model.Course
import com.example.mycollege.data.model.Marks
import com.example.mycollege.data.model.Task
import com.example.mycollege.data.repo.CourseRepository
import com.example.mycollege.data.repo.MarksRepository
import com.example.mycollege.data.repo.TasksRepository
import com.example.mycollege.presentation.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseScreenViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    private val tasksRepository: TasksRepository,
    private val marksRepository: MarksRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel()
{
    private val _state = MutableStateFlow(CourseScreenState())
    val state : StateFlow<CourseScreenState> = _state

    val navArgs: CourseScreenNavArgs = savedStateHandle.navArgs()

    init {
        fetchAll()
    }

    private fun fetchAll()
    {
        viewModelScope.launch {
            courseRepository.getCourseById(navArgs.courseId).let { course ->
                _state.value = _state.value.copy(
                    courseId = course.id,
                    courseName = course.name,
                    credits = course.credits.toString(),
                    targetGrade = course.targetGrade.toString(),
                    instructor = course.instructor,
                    classroom = course.classroom
                )
            }

            tasksRepository.getUpcomingTasks(navArgs.courseId)
                .onEach { upcomingTasks ->
                    _state.value = _state.value.copy(upcomingTasks = upcomingTasks)
                }.launchIn(viewModelScope)

            tasksRepository.getCompletedTasks(navArgs.courseId)
                .onEach { completedTasks ->
                    _state.value = _state.value.copy(completedTasks = completedTasks)
                }.launchIn(viewModelScope)

            marksRepository.getAllMarksByCourseId(navArgs.courseId)
                .onEach {marks ->
                    _state.value = _state.value.copy(allMarks = marks)
                }.launchIn(viewModelScope)
        }
    }

    fun onEvent(event: CourseScreenEvent) {
        when(event) {
            is CourseScreenEvent.OnCourseNameChange -> {
                _state.value = _state.value.copy(courseName = event.courseName)
            }
            is CourseScreenEvent.OnCreditsChange -> {
                _state.value = _state.value.copy(credits = event.credits.toString())
            }
            is CourseScreenEvent.OnTargetGradeChange -> {
                _state.value = _state.value.copy(targetGrade = event.targetGrade.toString())
            }
            is CourseScreenEvent.OnInstructorChange -> {
                _state.value = _state.value.copy(instructor = event.instructor)
            }
            is CourseScreenEvent.OnClassroomChange -> {
                _state.value = _state.value.copy(classroom = event.classroom)
            }
            is CourseScreenEvent.SaveCourse -> {
                viewModelScope.launch {
                    courseRepository.upsertCourse(
                        Course(
                            id = _state.value.courseId,
                            name = _state.value.courseName,
                            credits = _state.value.credits.toInt(),
                            targetGrade = _state.value.targetGrade.toInt(),
                            instructor = _state.value.instructor,
                            classroom = _state.value.classroom
                        )
                    )
                }
            }
            is CourseScreenEvent.DeleteCourse -> {
                _state.value.courseId?.let {
                    viewModelScope.launch {
                        courseRepository.deleteCourse(it)
                    }
                }
                _state.value.upcomingTasks.forEach {task ->
                    viewModelScope.launch {
                        if (task.id != null) {
                            tasksRepository.deleteTask(task.id)
                        }
                    }
                }
                _state.value.completedTasks.forEach {task ->
                    viewModelScope.launch {
                        if (task.id != null) {
                            tasksRepository.deleteTask(task.id)
                        }
                    }
                }
                _state.value.allMarks.forEach { marks ->
                    viewModelScope.launch {
                        if (marks.id != null) {
                            marksRepository.deleteMark(marks.id)
                        }
                    }
                }
            }


            is CourseScreenEvent.OnTaskNameChange -> {
                _state.value = _state.value.copy(taskName = event.taskName)
            }
            is CourseScreenEvent.OnDueDateChange -> {
                _state.value = _state.value.copy(dueDate = event.dueDate)
            }
            is CourseScreenEvent.OnTaskIdChange -> {
                if( event.taskId != null )
                {
                    viewModelScope.launch {
                        tasksRepository.getTaskById(event.taskId).let { task ->
                            _state.value = _state.value.copy(
                                taskId = event.taskId,
                                taskName = task.name,
                                dueDate = task.dueDate,
                                isCompleted = task.isCompleted
                            )
                        }
                    }
                }
                else
                {
                    _state.value = _state.value.copy(
                        taskId = null,
                        taskName = "",
                        dueDate = 0,
                        isCompleted = false
                    )
                }
            }
            is CourseScreenEvent.OnTaskStatusChange -> {
                viewModelScope.launch {
                    tasksRepository.upsertTask(task = event.task.copy(isCompleted = !event.task.isCompleted))

                    tasksRepository.getUpcomingTasks(navArgs.courseId)
                        .onEach { upcomingTasks ->
                            _state.value = _state.value.copy(upcomingTasks = upcomingTasks)
                        }.launchIn(viewModelScope)

                    tasksRepository.getCompletedTasks(navArgs.courseId)
                        .onEach { completedTasks ->
                            _state.value = _state.value.copy(completedTasks = completedTasks)
                        }.launchIn(viewModelScope)

                }
            }
            is CourseScreenEvent.SaveTask -> {
                viewModelScope.launch {
                    tasksRepository.upsertTask(
                        Task(
                            id = _state.value.taskId,
                            name = _state.value.taskName,
                            dueDate = _state.value.dueDate,
                            courseID = navArgs.courseId,
                            isCompleted = _state.value.isCompleted
                        )
                    )

                    _state.value = _state.value.copy(
                        taskName = "",
                        dueDate = 0,
                        isCompleted = false
                    )
                }
            }
            is CourseScreenEvent.DeleteTask -> {
                event.task.id.let { id ->
                    viewModelScope.launch {
                        if (id != null) {
                            tasksRepository.deleteTask(id)
                        }
                    }
                }
            }


            is CourseScreenEvent.OnMarkNameChange -> {
                _state.value = _state.value.copy(markName = event.markName)
            }
            is CourseScreenEvent.OnGotChange -> {
                _state.value = _state.value.copy(got = event.got)
            }
            is CourseScreenEvent.OnOutOfChange -> {
                _state.value = _state.value.copy(outOf = event.outOf)
            }
            is CourseScreenEvent.OnWeightageChange -> {
                _state.value = _state.value.copy(weightage = event.weightage)
            }
            is CourseScreenEvent.OnMarkIdChange -> {
                if( event.markId != null )
                {
                    viewModelScope.launch {
                        _state.value.courseId?.let {
                            marksRepository.getMarksByCourseIdAndId(it, event.markId).let { mark ->
                                _state.value = _state.value.copy(
                                    markId = event.markId,
                                    markName = mark.name,
                                    got = mark.got.toString(),
                                    outOf = mark.outOf.toString(),
                                    weightage = mark.weightage.toString(),
                                    total = mark.total
                                )
                            }
                        }
                    }
                }
                else
                {
                    _state.value = _state.value.copy(
                        markId = null,
                        markName = "",
                        got = "",
                        outOf = "",
                        weightage = ""
                    )
                }
            }
            is CourseScreenEvent.SaveMarks -> {
                viewModelScope.launch {
                    marksRepository.upsertMarks(
                        Marks(
                            id = _state.value.markId,
                            name = _state.value.markName,
                            got = _state.value.got.toDouble(),
                            outOf = _state.value.outOf.toDouble(),
                            weightage = _state.value.weightage.toDouble(),
                            courseID = navArgs.courseId,
                            total = ( _state.value.got.toDouble() / _state.value.outOf.toDouble() ) * _state.value.weightage.toDouble()
                        )
                    )
                }
            }
            is CourseScreenEvent.DeleteMarks -> {
                _state.value.markId.let {
                    viewModelScope.launch {
                        if (it != null) {
                            marksRepository.deleteMark(it)
                        }
                    }
                }
            }
        }
    }
}