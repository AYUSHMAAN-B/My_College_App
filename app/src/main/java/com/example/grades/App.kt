package com.example.grades

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.grades.Data.Course
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App()
{
    val navController : NavController = rememberNavController()
    val viewModel : MainViewModel = viewModel()
    val currentScreen = remember { mutableStateOf(viewModel.currentScreen.value) }
    val scaffoldState = rememberScaffoldState()
    var expanded by remember { mutableStateOf(false) }

    var courseName by remember { mutableStateOf("") }
    var courseCredits by remember { mutableStateOf("") }
    var courseGrade by remember { mutableStateOf("") }
    var semester by remember { mutableStateOf("") }

    var option by remember { mutableStateOf("Semester 1") }

    val courses = viewModel.getAllCourses.collectAsState(initial = listOf()).value

    val showDialog = remember { mutableStateOf(false) }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow
            .map { it.destination.route }
            .distinctUntilChanged()
            .collect { route ->
                currentScreen.value = Screens.fromRoute(route)
            }
    }

    if(showDialog.value)
    {
        AlertDialog(
            onDismissRequest = { showDialog.value = false},
            containerColor = Color(0xff219ebc),
            title = { Text(text = "Add Grade", color = Color(0xff023047)) },
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
                            viewModel.addCourse(
                                Course(
                                    id = courses.size + 1,
                                    name = courseName,
                                    credits = courseCredits.toInt(),
                                    grade = courseGrade.toInt(),
                                    sem = semester.toInt())
                            )

                            courseName = ""
                            courseCredits = ""
                            courseGrade = ""
                            semester = ""
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

    Scaffold(
        topBar = {
             TopAppBar(
                 title = { Text(text = currentScreen.value.title, color = Color.White) },
                 navigationIcon = {
                     Icon(
                         painter = painterResource(id = R.drawable.ic_icon),
                         contentDescription = null,
                         tint = Color.White,
                         modifier = Modifier
                             .padding(8.dp)
                             .clickable { }
                     )
                 },
                 colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xff023047))
             ) },
        scaffoldState = scaffoldState,
        floatingActionButton = {
            if( currentScreen.value.title == "GRADES" )
            {
                FloatingActionButton(
                    onClick = { showDialog.value = true },
                    backgroundColor = Color(0xff023047),
                    contentColor = Color.White
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "Add Grade")
                }
            }
        }
    ) {
        Navigation(
            navController = navController,
            padding = it,
            viewModel = viewModel,
            currentScreen = currentScreen)
    }
}