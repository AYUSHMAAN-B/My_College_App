package com.example.grades.Grade_Screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grades.Data.Course
import com.example.grades.MainViewModel
import com.example.grades.ui.theme.overallBackground

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Grades(
    viewModel: MainViewModel
) {
    val courses = viewModel.getAllCourses.collectAsState(initial = listOf()).value

    val sem1Courses = courses.filter { it.sem == 1 }
    val sem2Courses = courses.filter { it.sem == 2 }
    val sem3Courses = courses.filter { it.sem == 3 }
    val sem4Courses = courses.filter { it.sem == 4 }
    val sem5Courses = courses.filter { it.sem == 5 }
    val sem6Courses = courses.filter { it.sem == 6 }
    val sem7Courses = courses.filter { it.sem == 7 }
    val sem8Courses = courses.filter { it.sem == 8 }

    val credits by remember { mutableStateOf(viewModel.credits) }
    val spi by remember { mutableStateOf(viewModel.spi) }
    val cpi by remember { mutableStateOf(viewModel.cpi) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(overallBackground)
    ) {
        stickyHeader {
            Card(
                modifier = Modifier
                    .padding(8.dp),
                backgroundColor = Color(0xff023047)
            ) {
                Text(
                    text = "Semester 1",
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            LazyRow {
                items(sem1Courses){
                        course ->
                    CourseCard(
                        course = course,
                        viewModel = viewModel )
                }
            }

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color(0xff023047))
            ) {
                SPI_CPI(courses, 1, credits, spi, cpi)
            }
        }

        stickyHeader {
            Card(
                modifier = Modifier
                    .padding(8.dp),
                backgroundColor = Color(0xff023047)
            ) {
                Text(
                    text = "Semester 2",
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            LazyRow {
                items(sem2Courses){
                        course ->
                    CourseCard(
                        course = course,
                        viewModel = viewModel )
                }
            }

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color(0xff023047))
            ) {
                SPI_CPI(courses, 2, credits, spi, cpi)
            }
        }

        stickyHeader {
            Card(
                modifier = Modifier
                    .padding(8.dp),
                backgroundColor = Color(0xff023047)
            ) {
                Text(
                    text = "Semester 3",
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            LazyRow {
                items(sem3Courses){
                        course ->
                    CourseCard(
                        course = course,
                        viewModel = viewModel )
                }
            }

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color(0xff023047))
            ) {
                SPI_CPI(courses, 3, credits, spi, cpi)
            }
        }

        stickyHeader {
            Card(
                modifier = Modifier
                    .padding(8.dp),
                backgroundColor = Color(0xff023047)
            ) {
                Text(
                    text = "Semester 4",
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            LazyRow {
                items(sem4Courses){
                        course ->
                    CourseCard(
                        course = course,
                        viewModel = viewModel )
                }
            }

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color(0xff023047))
            ) {
                SPI_CPI(courses, 4, credits, spi, cpi)
            }
        }

        stickyHeader {
            Card(
                modifier = Modifier
                    .padding(8.dp),
                backgroundColor = Color(0xff023047)
            ) {
                Text(
                    text = "Semester 5",
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            LazyRow {
                items(sem5Courses){
                        course ->
                    CourseCard(
                        course = course,
                        viewModel = viewModel )
                }
            }

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color(0xff023047))
            ) {
                SPI_CPI(courses, 5, credits, spi, cpi)
            }
        }

        stickyHeader {
            Card(
                modifier = Modifier
                    .padding(8.dp),
                backgroundColor = Color(0xff023047)
            ) {
                Text(
                    text = "Semester 6",
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            LazyRow {
                items(sem6Courses){
                        course ->
                    CourseCard(
                        course = course,
                        viewModel = viewModel )
                }
            }

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color(0xff023047))
            ) {
                SPI_CPI(courses, 6, credits, spi, cpi)
            }
        }

        stickyHeader {
            Card(
                modifier = Modifier
                    .padding(8.dp),
                backgroundColor = Color(0xff023047)
            ) {
                Text(
                    text = "Semester 7",
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            LazyRow {
                items(sem7Courses){
                        course ->
                    CourseCard(
                        course = course,
                        viewModel = viewModel )
                }
            }

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color(0xff023047))
            ) {
                SPI_CPI(courses, 7, credits, spi, cpi)
            }
        }

        stickyHeader {
            Card(
                modifier = Modifier
                    .padding(8.dp),
                backgroundColor = Color(0xff023047)
            ) {
                Text(
                    text = "Semester 8",
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            LazyRow {
                items(sem8Courses){
                        course ->
                    CourseCard(
                        course = course,
                        viewModel = viewModel )
                }
            }

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color(0xff023047))
            ) {
                SPI_CPI(courses, 8, credits, spi, cpi)
            }

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun CourseCard(
    course : Course,
    viewModel : MainViewModel
) {
    var showOptions by remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clip(RoundedCornerShape(5.dp))
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        showOptions = true
                    }
                )
            }
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xff023047))
                .padding(8.dp)
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(RoundedCornerShape(5.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .background(Color(0xff8ecae6))
                            .padding(8.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        Text(
                            text =  course.name,
                            color = Color.Black
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(RoundedCornerShape(5.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .background(Color(0xff8ecae6))
                            .padding(8.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        Text(
                            text = "Grade : " + course.grade.toString(),
                            color = Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(5.dp))
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .background(Color(0xff8ecae6))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    Text(
                        text = "Credits : " + course.credits.toString(),
                        color = Color.Black
                    )
                }
            }
        }
    }

    if( course.sem == 8 )
        Spacer(modifier = Modifier.height(100.dp))

    if(showOptions)
    {
        AlertDialog(
            onDismissRequest = { showOptions = false},
            containerColor = Color(0xff219ebc),
            title = { Text(text = "Choose", color = Color(0xff023047)) },
            text = { },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog.value = true
                        showOptions = false
                    }
                ) {
                    Text(text = "Edit")
                }
            },
            dismissButton = {
                Button(
                    onClick = { viewModel.deleteCourse(course.id)
                        showOptions = false }
                ) {
                    Text(text = "Delete")
                }
            }
        )
    }

    if(showDialog.value)
    {
        var courseName by remember { mutableStateOf(course.name) }
        var courseCredits by remember { mutableStateOf(course.credits.toString()) }
        var courseGrade by remember { mutableStateOf(course.grade.toString()) }
        var semester by remember { mutableStateOf(course.sem.toString()) }
        var expanded by remember { mutableStateOf(false) }
        var option by remember { mutableStateOf("Semester 1") }

        AlertDialog(
            onDismissRequest = { showDialog.value = false},
            containerColor = Color(0xff219ebc),
            title = { Text(text = "Edit Grade", color = Color(0xff023047)) },
            text = {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    Column (
                        modifier = Modifier
                            .wrapContentSize()
                            .background(Color(0xff023047)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = courseName,
                            label = { Text(text = "Course Name") },
                            onValueChange = { courseName = it },
                            singleLine = true,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color.White,
                                focusedBorderColor = Color(0xff219ebc),
                                unfocusedBorderColor = Color(0xff219ebc)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )

                        Row (
                            modifier = Modifier
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            OutlinedTextField(
                                value = courseCredits,
                                label = { Text(text = "Credits") },
                                onValueChange = { courseCredits = it },
                                singleLine = true,
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    textColor = Color.White,
                                    focusedBorderColor = Color(0xff219ebc),
                                    unfocusedBorderColor = Color(0xff219ebc)),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                            )

                            OutlinedTextField(
                                value = courseGrade,
                                label = { Text(text = "Grade") },
                                onValueChange = { courseGrade = it },
                                singleLine = true,
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    textColor = Color.White,
                                    focusedBorderColor = Color(0xff219ebc),
                                    unfocusedBorderColor = Color(0xff219ebc)),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                            )
                        }

                        Button(onClick = { expanded = true },
                            modifier = Modifier
                                .padding(bottom = 8.dp))
                        {
                            Text(option)
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Drop Down Arrow"
                            )
                        }

                        DropdownMenu(expanded = expanded,
                            onDismissRequest = {expanded = false})
                        {
                            DropdownMenuItem(
                                text = { Text("Semester 1") },
                                onClick = {
                                    semester = "1"
                                    expanded = false
                                    option = "Semester 1"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 2") },
                                onClick = {
                                    semester = "2"
                                    expanded = false
                                    option = "Semester 2"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 3") },
                                onClick = {
                                    semester = "3"
                                    expanded = false
                                    option = "Semester 3"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 4") },
                                onClick = {
                                    semester = "4"
                                    expanded = false
                                    option = "Semester 4"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 5") },
                                onClick = {
                                    semester = "5"
                                    expanded = false
                                    option = "Semester 5"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 6") },
                                onClick = {
                                    semester = "6"
                                    expanded = false
                                    option = "Semester 6"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 7") },
                                onClick = {
                                    semester = "7"
                                    expanded = false
                                    option = "Semester 7"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 8") },
                                onClick = {
                                    semester = "8"
                                    expanded = false
                                    option = "Semester 8"
                                }
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if( courseName.isNotEmpty()
                            && courseCredits.toInt() > 0
                            && courseGrade.toInt() > 0
                            && semester.isNotEmpty() )
                        {
                            viewModel.updateCourse(
                                Course(
                                    id = course.id,
                                    name = courseName,
                                    credits = courseCredits.toInt(),
                                    grade = courseGrade.toInt(),
                                    sem = semester.toInt()
                                )
                            )
                        }

                        courseName = ""
                        courseCredits = ""
                        courseGrade = ""
                        semester = ""
                        showDialog.value = false
                    }
                ) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog.value = false }
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}