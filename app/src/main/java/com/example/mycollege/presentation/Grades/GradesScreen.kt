package com.example.mycollege.presentation.Grades

import android.graphics.Color.alpha
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycollege.data.model.Grades
import com.example.mycollege.presentation.theme.appBackgroundColor
import com.example.mycollege.presentation.theme.cardColor
import com.example.mycollege.presentation.theme.appBackgroundColor
import com.example.mycollege.presentation.theme.topAppBarColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun GradesScreenRoute(
    navigator: DestinationsNavigator
) {
    val viewModel : GradesScreenViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()

    val credits = viewModel.credits
    val spi = viewModel.spi
    val cpi = viewModel.cpi

    GradesScreen(
        navigator = navigator,
        state = state.value,
        onEvent = viewModel::onEvent,
        credits = credits,
        spi = spi,
        cpi = cpi
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GradesScreen(
    navigator: DestinationsNavigator,
    state: GradesScreenState,
    onEvent : (GradesScreenEvent) -> Unit,
    credits : Array<Int>,
    spi : Array<Double>,
    cpi : Array<Double>,
) {
    val sem1Courses = state.grades.filter { it.sem == 1 }
    val sem2Courses = state.grades.filter { it.sem == 2 }
    val sem3Courses = state.grades.filter { it.sem == 3 }
    val sem4Courses = state.grades.filter { it.sem == 4 }
    val sem5Courses = state.grades.filter { it.sem == 5 }
    val sem6Courses = state.grades.filter { it.sem == 6 }
    val sem7Courses = state.grades.filter { it.sem == 7 }
    val sem8Courses = state.grades.filter { it.sem == 8 }

    var expanded by remember { mutableStateOf(false) }
    var option by remember { mutableStateOf("Semester 1") }
    val showDialog = remember { mutableStateOf(false) }

    if(showDialog.value)
    {
        AlertDialog(
            onDismissRequest = { showDialog.value = false},
            containerColor = appBackgroundColor,
            textContentColor = cardColor,
            title = { Text(text = "Add Grade", color = cardColor) },
            text = {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    Column (
                        modifier = Modifier
                            .wrapContentSize()
                            .background(appBackgroundColor),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = state.name,
                            label = { Text(text = "Course Name", color = cardColor.copy(alpha = 0.5f)) },
                            onValueChange = { onEvent(GradesScreenEvent.OnNameChange(it))  },
                            singleLine = true,
                            textStyle = TextStyle(color = cardColor),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = cardColor,
                                unfocusedBorderColor = cardColor,
                                cursorColor = cardColor
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                        )

                        Row (
                            modifier = Modifier
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            OutlinedTextField(
                                value = state.credits,
                                label = { Text(text = "Credits", color = cardColor.copy(alpha = 0.5f)) },
                                onValueChange = { onEvent(GradesScreenEvent.OnCreditsChange(it.toInt()))  },
                                singleLine = true,
                                textStyle = TextStyle(color = cardColor),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = cardColor,
                                    unfocusedBorderColor = cardColor,
                                    cursorColor = cardColor
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                            )

                            OutlinedTextField(
                                value = state.grade,
                                label = { Text(text = "Grade", color = cardColor.copy(alpha = 0.5f)) },
                                onValueChange = { onEvent(GradesScreenEvent.OnGradeChange(it.toInt()))  },
                                singleLine = true,
                                textStyle = TextStyle(color = cardColor),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = cardColor,
                                    unfocusedBorderColor = cardColor,
                                    cursorColor = cardColor
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                            )
                        }

                        Button(onClick = { expanded = true },
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = cardColor
                            )
                        ) {
                            Text(option, color = Color.White)
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Drop Down Arrow",
                                tint = Color.White
                            )
                        }

                        DropdownMenu(expanded = expanded,
                            onDismissRequest = {expanded = false})
                        {
                            DropdownMenuItem(
                                text = { Text("Semester 1") },
                                onClick = {
                                    onEvent(GradesScreenEvent.OnSemChange(1))
                                    expanded = false
                                    option = "Semester 1"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 2") },
                                onClick = {
                                    onEvent(GradesScreenEvent.OnSemChange(2))
                                    expanded = false
                                    option = "Semester 2"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 3") },
                                onClick = {
                                    onEvent(GradesScreenEvent.OnSemChange(3))
                                    expanded = false
                                    option = "Semester 3"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 4") },
                                onClick = {
                                    onEvent(GradesScreenEvent.OnSemChange(4))
                                    expanded = false
                                    option = "Semester 4"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 5") },
                                onClick = {
                                    onEvent(GradesScreenEvent.OnSemChange(5))
                                    expanded = false
                                    option = "Semester 5"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 6") },
                                onClick = {
                                    onEvent(GradesScreenEvent.OnSemChange(6))
                                    expanded = false
                                    option = "Semester 6"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 7") },
                                onClick = {
                                    onEvent(GradesScreenEvent.OnSemChange(7))
                                    expanded = false
                                    option = "Semester 7"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 8") },
                                onClick = {
                                    onEvent(GradesScreenEvent.OnSemChange(8))
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
                        if( state.name.isNotEmpty()
                            && state.credits.toIntOrNull()!! > 0
                            && state.grade.toIntOrNull()!! > 0
                            && state.sem.toIntOrNull()!! > 0 )
                        {
                            onEvent(GradesScreenEvent.SaveGrade)

                            option = "Semester 1"
                            showDialog.value = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = cardColor
                    )
                ) {
                    Text(text = "Add", color = Color.White)
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog.value = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = cardColor
                    )
                ) {
                    Text(text = "Cancel", color = Color.White)
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Grades",
                    modifier = Modifier.padding(16.dp)) },
                navigationIcon = {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { navigator.navigateUp() },
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = topAppBarColor
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog.value = true },
                containerColor = topAppBarColor
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add Course"
                )
            }
        }
    ) {paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .background(appBackgroundColor)
                .fillMaxSize()
        ) {
            stickyHeader {
                Text(
                    text = "Semester 1",
                    fontSize = 24.sp,
                    color = cardColor,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                )

                LazyRow {
                    items(sem1Courses){
                            course ->
                        CourseCard(
                            course = course,
                            state = state,
                            onEvent = onEvent
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(appBackgroundColor)
                ) {
                    SPI_CPI(state.grades, 1, credits, spi, cpi)
                }

                Divider(thickness = 2.dp, color = cardColor)
            }

            stickyHeader {
                Text(
                    text = "Semester 2",
                    fontSize = 24.sp,
                    color = cardColor,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                )

                LazyRow {
                    items(sem2Courses){
                            course ->
                        CourseCard(
                            course = course,
                            state = state,
                            onEvent = onEvent
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(appBackgroundColor)
                ) {
                    SPI_CPI(state.grades, 2, credits, spi, cpi)
                }

                Divider(thickness = 2.dp, color = cardColor)
            }

            stickyHeader {
                Text(
                    text = "Semester 3",
                    fontSize = 24.sp,
                    color = cardColor,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                )

                LazyRow {
                    items(sem3Courses){
                            course ->
                        CourseCard(
                            course = course,
                            state = state,
                            onEvent = onEvent
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(appBackgroundColor)
                ) {
                    SPI_CPI(state.grades, 3, credits, spi, cpi)
                }

                Divider(thickness = 2.dp, color = cardColor)
            }

            stickyHeader {
                Text(
                    text = "Semester 4",
                    fontSize = 24.sp,
                    color = cardColor,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                )

                LazyRow {
                    items(sem4Courses){
                            course ->
                        CourseCard(
                            course = course,
                            state = state,
                            onEvent = onEvent
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(appBackgroundColor)
                ) {
                    SPI_CPI(state.grades, 4, credits, spi, cpi)
                }

                Divider(thickness = 2.dp, color = cardColor)
            }

            stickyHeader {
                Text(
                    text = "Semester 5",
                    fontSize = 24.sp,
                    color = cardColor,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                )

                LazyRow {
                    items(sem5Courses){
                            course ->
                        CourseCard(
                            course = course,
                            state = state,
                            onEvent = onEvent
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(appBackgroundColor)
                ) {
                    SPI_CPI(state.grades, 5, credits, spi, cpi)
                }

                Divider(thickness = 2.dp, color = cardColor)
            }

            stickyHeader {
                Text(
                    text = "Semester 6",
                    fontSize = 24.sp,
                    color = cardColor,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                )

                LazyRow {
                    items(sem6Courses){
                            course ->
                        CourseCard(
                            course = course,
                            state = state,
                            onEvent = onEvent
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(appBackgroundColor)
                ) {
                    SPI_CPI(state.grades, 6, credits, spi, cpi)
                }

                Divider(thickness = 2.dp, color = cardColor)
            }

            stickyHeader {
                Text(
                    text = "Semester 7",
                    fontSize = 24.sp,
                    color = cardColor,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                )

                LazyRow {
                    items(sem7Courses){
                            course ->
                        CourseCard(
                            course = course,
                            state = state,
                            onEvent = onEvent
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(appBackgroundColor)
                ) {
                    SPI_CPI(state.grades, 7, credits, spi, cpi)
                }

                Divider(thickness = 2.dp, color = cardColor)
            }

            stickyHeader {
                Text(
                    text = "Semester 8",
                    fontSize = 24.sp,
                    color = cardColor,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                )

                LazyRow {
                    items(sem8Courses){
                            course ->
                        CourseCard(
                            course = course,
                            state = state,
                            onEvent = onEvent
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(appBackgroundColor)
                ) {
                    SPI_CPI(state.grades, 8, credits, spi, cpi)
                }

                Divider(thickness = 2.dp, color = cardColor)

                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
fun CourseCard(
    course : Grades,
    state : GradesScreenState,
    onEvent: (GradesScreenEvent) -> Unit
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
                .background(cardColor)
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(5.dp))
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .background(appBackgroundColor)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    Text(
                        text =  course.name,
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row{
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(RoundedCornerShape(5.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .background(appBackgroundColor)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        Text(
                            text = "Credits : " + course.credits.toString(),
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
                            .background(appBackgroundColor)
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
        }
    }

    if( course.sem == 8 )
        Spacer(modifier = Modifier.height(100.dp))

    if(showOptions)
    {
        AlertDialog(
            onDismissRequest = { showOptions = false},
            containerColor = Color(0xff219ebc),
            title = { Text(text = "Choose", color = appBackgroundColor) },
            text = { },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog.value = true
                        showOptions = false
                        onEvent(GradesScreenEvent.OnGradeIdChanged(course.id))
                    }
                ) {
                    Text(text = "Edit")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        course.id?.let {
                            onEvent(GradesScreenEvent.DeleteGrade(course.id))
                        }
                        showOptions = false }
                ) {
                    Text(text = "Delete")
                }
            }
        )
    }

    if(showDialog.value)
    {
        var expanded by remember { mutableStateOf(false) }
        var option by remember { mutableStateOf("") }

        when(state.sem) {
            "1" -> option = "Semester 1"
            "2" -> option = "Semester 2"
            "3" -> option = "Semester 3"
            "4" -> option = "Semester 4"
            "5" -> option = "Semester 5"
            "6" -> option = "Semester 6"
            "7" -> option = "Semester 7"
            "8" -> option = "Semester 8"
        }

        AlertDialog(
            onDismissRequest = { showDialog.value = false},
            containerColor = Color(0xff219ebc),
            title = { Text(text = "Add Grade", color = appBackgroundColor) },
            text = {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    Column (
                        modifier = Modifier
                            .wrapContentSize()
                            .background(appBackgroundColor),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = state.name,
                            label = { Text(text = "Course Name") },
                            onValueChange = { onEvent(GradesScreenEvent.OnNameChange(it))  },
                            singleLine = true,
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
                                value = state.credits.toString(),
                                label = { Text(text = "Credits") },
                                onValueChange = { onEvent(GradesScreenEvent.OnCreditsChange(it.toInt()))  },
                                singleLine = true,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                            )

                            OutlinedTextField(
                                value = state.grade.toString(),
                                label = { Text(text = "Grade") },
                                onValueChange = { onEvent(GradesScreenEvent.OnGradeChange(it.toInt()))  },
                                singleLine = true,
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
                                    onEvent(GradesScreenEvent.OnSemChange(1))
                                    expanded = false
                                    option = "Semester 1"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 2") },
                                onClick = {
                                    onEvent(GradesScreenEvent.OnSemChange(2))
                                    expanded = false
                                    option = "Semester 2"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 3") },
                                onClick = {
                                    onEvent(GradesScreenEvent.OnSemChange(3))
                                    expanded = false
                                    option = "Semester 3"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 4") },
                                onClick = {
                                    onEvent(GradesScreenEvent.OnSemChange(4))
                                    expanded = false
                                    option = "Semester 4"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 5") },
                                onClick = {
                                    onEvent(GradesScreenEvent.OnSemChange(5))
                                    expanded = false
                                    option = "Semester 5"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 6") },
                                onClick = {
                                    onEvent(GradesScreenEvent.OnSemChange(6))
                                    expanded = false
                                    option = "Semester 6"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 7") },
                                onClick = {
                                    onEvent(GradesScreenEvent.OnSemChange(7))
                                    expanded = false
                                    option = "Semester 7"
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Semester 8") },
                                onClick = {
                                    onEvent(GradesScreenEvent.OnSemChange(8))
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
                        if( state.name.isNotEmpty()
                            && state.credits.toIntOrNull()!! > 0
                            && state.grade.toIntOrNull()!! > 0
                            && state.sem.toIntOrNull()!! > 0 )
                        {
                            onEvent(GradesScreenEvent.SaveGrade)

                            option = "Semester 1"
                            showDialog.value = false
                        }
                    }
                ) {
                    Text(text = "Add")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog.value = false }
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}