package com.example.mycollege.presentation.Semester.Courses

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycollege.data.model.Course
import com.example.mycollege.presentation.theme.color1
import com.example.mycollege.presentation.theme.color2
import com.example.mycollege.presentation.theme.color3
import com.example.mycollege.presentation.theme.color4
import com.example.mycollege.timetable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import com.example.mycollege.data.model.Task
import com.ramcosta.composedestinations.annotation.Destination

data class CourseScreenNavArgs(
    val courseId: Int
)

@Destination(navArgsDelegate = CourseScreenNavArgs::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CourseScreenRoute(
    navigator: DestinationsNavigator
) {
    val viewModel : CourseScreenViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()

    CourseScreen(
        state = state.value,
        onEvent = viewModel::onEvent,
        navigator = navigator
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CourseScreen(
    state: CourseScreenState,
    onEvent : (CourseScreenEvent) -> Unit,
    navigator: DestinationsNavigator,
) {
    val course = Course(                                            // state variable [ Course ]
        id = 1,
        name = "Automata Theory",
        credits = 8,
        targetGrade = 10,
        classroom = "LG08",
        instructor = "Ayushmaan"
    )

    var editCourseDialogBox by remember { mutableStateOf(false) }

    val targetGradeError = when {
        state.targetGrade.toIntOrNull() == null -> "Target Grade must be a number between 0 and 10"
        state.targetGrade.toInt() !in 0..10 -> "Target Grade must be between 0 and 10"
        else -> null
    }

    if( editCourseDialogBox )
    {
        AlertDialog(
            onDismissRequest = { editCourseDialogBox = false },
            title = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Edit Course",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = state.courseName,
                        onValueChange = { onEvent(CourseScreenEvent.OnCourseNameChange(it)) },
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
                            onValueChange = { onEvent(CourseScreenEvent.OnCreditsChange(it)) },
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
                            onValueChange = { onEvent(CourseScreenEvent.OnTargetGradeChange(it)) },
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
                        onValueChange = { onEvent(CourseScreenEvent.OnClassroomChange(it)) },
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
                        onValueChange = { onEvent(CourseScreenEvent.OnInstructorChange(it)) },
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
                        onEvent(CourseScreenEvent.SaveCourse)
                        editCourseDialogBox = false
                    },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = color1,
                        containerColor = color4
                    )
                ) {
                    Text(
                        text = "Save",
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = { editCourseDialogBox = false },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = color1,
                        containerColor = color4
                    )
                ) {
                    Text(
                        text = "Cancel",
                    )
                }
            },
            containerColor = color4,
            titleContentColor = color1,
            textContentColor = color3,
            tonalElevation = 24.dp
        )
    }

    var deleteCourseDialogBox by remember { mutableStateOf(false) }

    if( deleteCourseDialogBox )
    {
        AlertDialog(
            onDismissRequest = { deleteCourseDialogBox = false },
            title = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Delete Course",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center),
                        color = color1
                    )
                }
            },
            text = {
                Text(
                    text = "Are you sure you want to delete this course?",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = color1
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onEvent(CourseScreenEvent.DeleteCourse)
                        navigator.navigateUp()
                        deleteCourseDialogBox = false
                    },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = color1,
                        containerColor = color4
                    )
                ) {
                    Text(
                        text = "Yes",
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = { deleteCourseDialogBox = false },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = color1,
                        containerColor = color4
                    )
                ) {
                    Text(
                        text = "No",
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
            TopAppBar(
                navigationIcon = {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { navigator.navigateUp() },
                        tint = color4
                    )
                },
                title = { Text(
                    state.courseName,
                    color = color4
                )
                },
                actions = {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { editCourseDialogBox = true },
                        tint = color4
                    )
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { deleteCourseDialogBox = true },
                        tint = color4
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = color2
                )
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color1)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .background(color1)
            ) {
                item {
                    course.id?.let {
                        Tasks(
                            state = state,
                            onEvent = onEvent
                        )
                    }
                }

                item {
                    MarksList(
                        state = state,
                        onEvent = onEvent
                    )
                }

//                item {
//                    course.id?.let {
//                        TimeTable(
//                            courseId = course.id,
//                            timetable = timetable
//                        )
//                    }
//                }
            }
        }
    }
}

//@Composable
//fun TimeTable(
//    courseId: Int,
//    timetable: MutableList<Slot>
//) {
//    var dialogBox by remember { mutableStateOf(false) }
//
//    if (dialogBox) {
//        AlertDialog(
//            onDismissRequest = { dialogBox = false },
//            title = { Text("Enter details") },
//            text = {
//                Column {
//                    TimetableRow("Monday", timetable, courseId)
//                    TimetableRow("Tuesday", timetable, courseId)
//                    TimetableRow("Wednesday", timetable, courseId)
//                    TimetableRow("Thursday", timetable, courseId)
//                    TimetableRow("Friday", timetable, courseId)
//                }
//            },
//            dismissButton = {},
//            confirmButton = {}
//        )
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text("TIME TABLE",
//                color = color4,
//                fontSize = 20.sp)
//            Icon(
//                Icons.Default.Edit,
//                contentDescription = null,
//                modifier = Modifier
//                    .clickable { dialogBox = true }
//                    .size(25.dp),
//                tint = color4,
//            )
//        }
//
//        var monday = false
//        var tuesday = false
//        var wednesday = false
//        var thursday = false
//        var friday = false
//
//        val selectedMondaySlots = timetable.filter { it.day == "Monday" && it.courseID == courseId }
//        val selectedTuesdaySlots = timetable.filter { it.day == "Tuesday" && it.courseID == courseId }
//        val selectedWednesdaySlots = timetable.filter { it.day == "Wednesday" && it.courseID == courseId }
//        val selectedThursdaySlots = timetable.filter { it.day == "Thursday" && it.courseID == courseId }
//        val selectedFridaySlots = timetable.filter { it.day == "Friday" && it.courseID == courseId }
//
//        if (selectedMondaySlots.isNotEmpty()) {
//            DayCard("Monday", selectedMondaySlots)
//            monday = true
//        }
//        if (selectedTuesdaySlots.isNotEmpty()) {
//            DayCard("Tuesday", selectedTuesdaySlots)
//            tuesday = true
//        }
//        if (selectedWednesdaySlots.isNotEmpty()) {
//            DayCard("Wednesday", selectedWednesdaySlots)
//            wednesday = true
//        }
//        if (selectedThursdaySlots.isNotEmpty()) {
//            DayCard("Thursday", selectedThursdaySlots)
//            thursday = true
//        }
//        if (selectedFridaySlots.isNotEmpty()) {
//            DayCard("Friday", selectedFridaySlots)
//            friday = true
//        }
//
//        if (!monday && !tuesday && !wednesday && !thursday && !friday)
//        {
//            Text(
//                "You have not set your timetable yet. \n Click on the 'Edit' icon to set your timetable.",
//                modifier = Modifier.fillMaxWidth(),
//                textAlign = TextAlign.Center,
//                color = color4.copy(alpha = 0.75f)
//            )
//        }
//    }
//}
//
//@Composable
//fun TimetableRow(day: String, slots: MutableList<Slot>, courseId: Int)
//{
//    Text(text = day)
//    LazyRow(
//        modifier = Modifier
//            .padding(horizontal = 8.dp) // Adjust padding as needed
//    ) {
//        items(slots) { slot ->
//            if( slot.day == day && ( slot.courseID == null || slot.courseID == courseId ) )
//            {
//                Box(
//                    modifier = Modifier
//                        .padding(horizontal = 4.dp)
//                        .border(
//                            width = 2.dp,
//                            shape = RoundedCornerShape(8.dp),
//                            color = if (slot.courseID != null) Color.White else Color.Transparent
//                        )
//                        .clickable {
//                            if (slot.courseID != null) {
//                                slot.courseID = null
//                            } else {
//                                slot.courseID = courseId
//                            }
//                        }
//                ) {
//                    Text(
//                        text = "${slot.startTime} - ${slot.endTime}",
//                        modifier = Modifier.padding(8.dp)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun DayCard(day: String, slots: List<Slot>)
//{
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(4.dp)
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Text(day)
//            LazyRow {
//                items(slots) {
//                    Text(
//                        text = "${it.startTime} - ${it.endTime}",
//                        modifier = Modifier.padding(8.dp)
//                    )
//                }
//            }
//        }
//    }
//}

@Composable
fun MarksList(
    state: CourseScreenState,
    onEvent: (CourseScreenEvent) -> Unit
) {
    var marksDialogBox by remember { mutableStateOf(false) }

    val gotError = when {
        state.got.toDoubleOrNull() == null -> "Got must be a number"
        else -> null
    }

    val outOfError = when {
        state.outOf.toDoubleOrNull() == null -> "OutOf must be a number"
        state.outOf.toDoubleOrNull() == 0.0 -> "OutOf must be greater than 0"
        else -> null
    }

    if( marksDialogBox )
    {
        AlertDialog(
            onDismissRequest = { marksDialogBox = false },
            title = { Text("Enter details") },
            text = {
                Column {
                    OutlinedTextField(
                        value = state.markName,
                        onValueChange = { onEvent(CourseScreenEvent.OnMarkNameChange(it)) },
                        label = { Text("Name", color = color1) }
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = state.got,
                            onValueChange = { onEvent(CourseScreenEvent.OnGotChange(it)) },
                            label = { Text("Got", color = color1) },
                            isError = gotError != null,
                            supportingText = { Text(gotError ?: "") },
                            modifier = Modifier
                                .weight(1f)
                        )
                        OutlinedTextField(
                            value = state.outOf,
                            onValueChange = { onEvent(CourseScreenEvent.OnOutOfChange(it)) },
                            label = { Text("OutOf", color = color1) },
                            isError = outOfError != null,
                            supportingText = { Text(outOfError ?: "") },
                            modifier = Modifier
                                .weight(1f)
                        )
                        OutlinedTextField(
                            value = state.weightage,
                            onValueChange = { onEvent(CourseScreenEvent.OnWeightageChange(it)) },
                            label = { Text("Weight", color = color1) },
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onEvent(CourseScreenEvent.SaveMarks)
                        marksDialogBox = false
                    },
                    enabled = gotError == null && outOfError == null,
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = color1,
                        containerColor = color4
                    )
                ) {
                    Text(
                        text = "Save",
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = { marksDialogBox = false },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = color1,
                        containerColor = color4
                    )
                ) {
                    Text(
                        text = "Cancel",
                    )
                }
            },
            containerColor = color4,
            titleContentColor = color1,
            textContentColor = color3,
            tonalElevation = 24.dp
        )
    }

    var deleteMarksDialogBox by remember { mutableStateOf(false) }

    if( deleteMarksDialogBox )
    {
        AlertDialog(
            onDismissRequest = { deleteMarksDialogBox = false },
            title = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Delete Course",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            text = {
                Text(
                    text = "Are you sure you want to delete this marks?",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = color1
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onEvent(CourseScreenEvent.DeleteMarks)
                        deleteMarksDialogBox = false
                    },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = color1,
                        containerColor = color4
                    )
                ) {
                    Text(
                        text = "Yes",
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = { deleteMarksDialogBox = false },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = color1,
                        containerColor = color4
                    )
                ) {
                    Text(
                        text = "No",
                    )
                }
            },
            containerColor = color4,
            titleContentColor = color1,
            textContentColor = color3,
            tonalElevation = 24.dp
        )
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("MARKS",
                color = color4,
                fontSize = 20.sp
            )
            Icon(
                Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.clickable {
                    onEvent(CourseScreenEvent.OnMarkIdChange(null))
                    marksDialogBox = true }
                    .size(25.dp),
                tint = color4
            )
        }

        if(state.allMarks.isNotEmpty())
        {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = color2
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    state.allMarks.forEach { mark ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onTap = {
                                            onEvent(CourseScreenEvent.OnMarkIdChange(mark.id))
                                            marksDialogBox = true
                                        },
                                        onLongPress = {
                                            onEvent(CourseScreenEvent.OnMarkIdChange(mark.id))
                                            deleteMarksDialogBox = true
                                        }
                                    )
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${mark.name} :",
                                style = MaterialTheme.typography.bodyLarge,
                                color = color4,
                                modifier = Modifier.weight(2f)
                            )
                            Text(
                                text = "${mark.got} / ${mark.outOf}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = color4,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "@ ${mark.weightage} % ",
                                style = MaterialTheme.typography.bodyMedium,
                                color = color4,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Icon(
                                Icons.Default.KeyboardArrowRight,
                                contentDescription = null,
                                modifier = Modifier.weight(0.5f),
                                tint = color4
                            )
                            Text(
                                text = String.format("%.2f", mark.total),
                                style = MaterialTheme.typography.bodyLarge,
                                color = color4,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.End
                            )
                        }
                        Divider(color = color4, thickness = 1.dp, modifier = Modifier.padding(top = 4.dp))
                    }

                    val finalTotal = state.allMarks.sumOf { it.total }
                    val finalOutOf = state.allMarks.sumOf { it.weightage }

                    Divider(color = color4, thickness = 2.dp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total    :    " + String.format("%.2f", finalTotal) + " / $finalOutOf",
                            color = color4,
                            fontSize = 20.sp,
                            modifier = Modifier.weight(2f),
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
        }
        else
        {
            Text(
                text = "You have not added any test results yet. \n Click on the '+' icon to add a new exam result.",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = color4.copy(alpha = 0.75f)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tasks(
    state: CourseScreenState,
    onEvent: (CourseScreenEvent) -> Unit
) {
    var taskDialogBox by remember { mutableStateOf(false) }

    var isDatePickerDialogOpen by rememberSaveable { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )

    if (isDatePickerDialogOpen)
    {
        DatePickerDialog(
            onDismissRequest = { isDatePickerDialogOpen = false },
            confirmButton = {
                TextButton(onClick = {
                    val date = datePickerState.selectedDateMillis
                    date?.let {
                        onEvent(CourseScreenEvent.OnDueDateChange(it))
                    }
                    isDatePickerDialogOpen = false
                }) {
                    Text(text = "Select")
                }
            },
            dismissButton = {
                TextButton(onClick = { isDatePickerDialogOpen = false }) {
                    Text(text = "Cancel")
                }
            },
            colors = DatePickerDefaults.colors(
                containerColor = color4,
                titleContentColor = color1
            )
        ) {
            DatePicker(
                state = datePickerState,
                dateValidator = { timestamp ->
                    val selectedDate = Instant
                        .ofEpochMilli(timestamp)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                    val currentDate = LocalDate.now(ZoneId.systemDefault())
                    selectedDate >= currentDate
                }
            )
        }
    }

    if( taskDialogBox )
    {
        AlertDialog(
            onDismissRequest = { taskDialogBox = false },
            title = { Text("Enter details") },
            text = {
                Column {
                    OutlinedTextField(
                        value = state.taskName,
                        onValueChange = { onEvent(CourseScreenEvent.OnTaskNameChange(it)) },
                        label = { Text("Task Name",
                            color = color1) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Due Date",
                        style = MaterialTheme.typography.bodySmall,
                        color = color1
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = state.dueDate.changeMillisToDateString(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = color1
                        )
                        IconButton(onClick = { isDatePickerDialogOpen = true }) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Select Due Date",
                                tint = color1
                            )
                        }
                    }
                }
            },
            dismissButton = {
                Button(
                    onClick = { taskDialogBox = false },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = color1,
                        containerColor = color4
                    )
                ) {
                    Text("Cancel")
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onEvent(CourseScreenEvent.SaveTask)
                        taskDialogBox = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = color1,
                        containerColor = color4
                    )
                ) {
                    Text("Save")
                }
            },
            containerColor = color4,
            titleContentColor = color1,
            textContentColor = color3,
            tonalElevation = 24.dp
        )
    }

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("UPCOMING TASKS",
                color = color4,
                fontSize = 20.sp
            )
            Icon(
                Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.clickable {
                    onEvent(CourseScreenEvent.OnTaskIdChange( null ))
                    taskDialogBox = true }
                    .size(25.dp),
                tint = color4
            )
        }

        if(state.upcomingTasks.isEmpty())
        {
            Text("You have no Upcoming tasks as of now. \n Click on the '+' icon to add a new task.",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = color4.copy(alpha = 0.75f)
            )
        }

        state.upcomingTasks.forEach { task ->
            TaskCard(
                task = task,
                onTaskCardClicked = {
                    onEvent(CourseScreenEvent.OnTaskIdChange(task.id))
                    taskDialogBox = true
                },
                onTaskStatusChange = { onEvent(CourseScreenEvent.OnTaskStatusChange(task)) },
                onDeleteTask = { onEvent(CourseScreenEvent.DeleteTask(task)) }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("COMPLETED TASKS",
                color = color4,
                fontSize = 20.sp
            )
        }

        if(state.completedTasks.isEmpty())
        {
            Text("You have no Completed tasks as of now. \n Complete the tasks to achieve your goal.",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = color4.copy(alpha = 0.75f)
            )
        }

        state.completedTasks.forEach {task ->
            TaskCard(
                task = task,
                onTaskCardClicked = {
                    onEvent(CourseScreenEvent.OnTaskIdChange(task.id))
                    taskDialogBox = true
                },
                onTaskStatusChange = { onEvent(CourseScreenEvent.OnTaskStatusChange(task)) },
                onDeleteTask = { onEvent(CourseScreenEvent.DeleteTask(task)) }
            )
        }
    }
}

@Composable
fun TaskCard(
    task : Task,
    onTaskCardClicked : () -> Unit,
    onTaskStatusChange : () -> Unit,
    onDeleteTask : () -> Unit,
) {
    var deleteTaskBox by remember { mutableStateOf(false) }

    if( deleteTaskBox )
    {
        AlertDialog(
            onDismissRequest = { deleteTaskBox = false },
            title = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Delete Task",
                        color = color1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            text = {
                Text(
                    text = "Are you sure you want to delete this task?",
                    textAlign = TextAlign.Center,
                    color = color1,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onDeleteTask()
                        deleteTaskBox = false
                    },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = color1,
                        containerColor = color4
                    )
                ) {
                    Text(
                        text = "Yes",
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = { deleteTaskBox = false },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = color1,
                        containerColor = color4
                    )
                ) {
                    Text(
                        text = "No",
                    )
                }
            },
            containerColor = color4,
            titleContentColor = color1,
            textContentColor = color3,
            tonalElevation = 24.dp
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(2.dp, color4, RoundedCornerShape(8.dp))
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        onTaskCardClicked()
                    },
                    onLongPress = {
                        deleteTaskBox = true
                    }
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = color2
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape)
                    .border(2.dp, color4, CircleShape)
                    .clickable {
                        onTaskStatusChange()
                    },
            ) {
                if( task.isCompleted )
                {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        imageVector = Icons.Rounded.Check,
                        contentDescription = null,
                        tint = color4
                    )
                }
            }
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(task.name,
                    textDecoration = if( task.isCompleted ) TextDecoration.LineThrough else TextDecoration.None,
                    color = color4,
                    fontSize = 20.sp
                )
                Text(task.dueDate.changeMillisToDateString(),
                    color = color4
                )
            }
        }
    }
}

@SuppressLint("NewApi")
fun Long?.changeMillisToDateString(): String {
    val date: LocalDate = this?.let {
        Instant
            .ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    } ?: LocalDate.now()
    return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
}