package com.example.mycollege.presentation.Semester.AllCourses

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycollege.data.model.Course
import com.example.mycollege.presentation.Semester.Courses.CourseScreenNavArgs
import com.example.mycollege.presentation.theme.color1
import com.example.mycollege.presentation.theme.color2
import com.example.mycollege.presentation.theme.color3
import com.example.mycollege.presentation.theme.color4
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.mycollege.presentation.destinations.CourseScreenRouteDestination
import com.example.mycollege.presentation.theme.cardColor

@Destination
@Composable
fun AllCoursesScreenRoute(
    navigator: DestinationsNavigator
) {
    val viewModel : AllCoursesViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()

    AllCoursesScreen(
        state = state.value,
        onEvent = viewModel::onEvent,
        navigator = navigator,
        onCourseCardClicked = { course ->
            course.id?.let {
                val navArgs = CourseScreenNavArgs(courseId = it)
                navigator.navigate(
                    CourseScreenRouteDestination(
                        navArgs = navArgs
                    )
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AllCoursesScreen(
    state : AllCoursesScreenState,
    onEvent: (AllCoursesScreenEvent) -> Unit,
    navigator: DestinationsNavigator,
    onCourseCardClicked: (Course) -> Unit
) {
    var addCourseDialogBox by remember { mutableStateOf(false) }

    val targetGradeError = when {
        state.targetGrade.toIntOrNull() == null -> "Target Grade must be a number between 0 and 10"
        state.targetGrade.toInt() !in 0..10 -> "Target Grade must be between 0 and 10"
        else -> null
    }

    if( addCourseDialogBox )
    {
        AlertDialog(
            onDismissRequest = { addCourseDialogBox = false },
            title = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Add Course",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = state.name,
                        onValueChange = { onEvent(AllCoursesScreenEvent.OnNameChange(it)) },
                        label = {
                            Text(
                                text = "Course Name",
                                color = Color.White.copy(0.8f)
                            )
                        },
                        singleLine = true,
                        modifier = Modifier.padding(8.dp)
                    )
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = state.credits,
                            onValueChange = { onEvent(AllCoursesScreenEvent.OnCreditsChange(it)) },
                            label = {
                                Text(
                                    text = "Credits",
                                    color = Color.White.copy(0.8f)
                                )
                            },
                            singleLine = true,
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(1f)
                        )
                        OutlinedTextField(
                            value = state.targetGrade,
                            onValueChange = { onEvent(AllCoursesScreenEvent.OnTargetGradeChange(it)) },
                            label = {
                                Text(
                                    text = "Target Grade",
                                    color = Color.White.copy(0.8f)
                                )
                            },
                            isError = targetGradeError != null,
                            supportingText = { Text(targetGradeError ?: "") },
                            singleLine = true,
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(1f)
                        )
                    }
                    OutlinedTextField(
                        value = state.classroom ?: "",
                        onValueChange = { onEvent(AllCoursesScreenEvent.OnClassroomChange(it)) },
                        label = {
                            Text(
                                text = "Classroom",
                                color = Color.White.copy(0.8f)
                            )
                        },
                        singleLine = true,
                        modifier = Modifier.padding(8.dp)
                    )
                    OutlinedTextField(
                        value = state.instructor ?: "",
                        onValueChange = { onEvent(AllCoursesScreenEvent.OnInstructorChange(it)) },
                        label = {
                            Text(
                                text = "Instructor",
                                color = Color.White.copy(0.8f)
                            )
                        },
                        singleLine = true,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onEvent(AllCoursesScreenEvent.AddCourse)
                        addCourseDialogBox = false
                    },
                    modifier = Modifier.padding(8.dp),
                    enabled = targetGradeError == null,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = color1,
                        containerColor = color4
                    )
                ) {
                    Text(
                        text = "Save"
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = { addCourseDialogBox = false },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = color1,
                        containerColor = color4
                    )
                ) {
                    Text(
                        text = "Cancel"
                    )
                }
            },
            containerColor = color4,
            titleContentColor = color1,
            textContentColor = color3,
            tonalElevation = 24.dp
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Your Courses",
                        style = MaterialTheme.typography.titleLarge,
                        color = color4
                    )
                },
                navigationIcon = {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "go back",
                        tint = cardColor,
                        modifier = Modifier.clickable {
                            navigator.navigateUp()
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = color2
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(
                        text = "Add Course",
                        color = color4
                    )
                },
                icon = {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "go back",
                        tint = color4
                    )
                },
                onClick = { addCourseDialogBox = true },
                containerColor = color2
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color1)
        ) {
            LazyColumn (
                modifier = Modifier
                    .padding(paddingValues)
                    .background(color1)
            ) {
                state.courses?.let {
                    items(state.courses) {
                        CourseCard(
                            course = it,
                            onCourseCardClicked = onCourseCardClicked
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CourseCard(
    course: Course,
    onCourseCardClicked: (Course) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onCourseCardClicked(course) },
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = color2
        )
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = course.name,
                fontSize = 28.sp,
                color = color4,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Divider(color = color4, thickness = 1.dp)
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Credits : ${course.credits}",
                        color = color4,
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Text(
                        text = "Target Grade : ${course.targetGrade?.toString() ?: "N/A"}",
                        fontSize = 16.sp,
                        color = color4,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Classroom : ${course.classroom ?: "TBA"}",
                        fontSize = 16.sp,
                        color = color4,
                        modifier = Modifier.align(Alignment.End)
                    )
                    Text(
                        text = "Instructor : ${course.instructor ?: "TBA"}",
                        fontSize = 16.sp,
                        color = color4,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
        }
    }
}