package com.example.grades.ToDo_Screen

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grades.R
import com.example.grades.ui.theme.todoBackground

@Composable
fun ToDo()
{
    val ToDos = remember { mutableListOf<String>() }
    var todo by remember { mutableStateOf("") }
    var AddTaskClicked by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(todoBackground)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Tasks",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(12.dp))

            ToDos.forEach {
                Card(
                    modifier = Modifier
                        .width(500.dp)
                        .height(60.dp)
                        .padding(top = 4.dp),
                    shape = MaterialTheme.shapes.extraSmall,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xff373737)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            it,
                            fontSize = 20.sp,
                            color = Color.White // Optional: Add color for better visibility
                        )
                    }
                }
            }
        }

        if( !AddTaskClicked )
        {
            OutlinedButton(
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black,
                    backgroundColor = Color(0xff373737)
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
                    .height(40.dp)
                    .clip(shape = MaterialTheme.shapes.extraLarge),
                onClick = { AddTaskClicked = true }
            ) {
                Text(
                    "Add a Task",
                    color = Color.White
                )
            }
        }

        if( AddTaskClicked )
        {
            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(75.dp),
                shape = MaterialTheme.shapes.extraSmall,
                colors = CardDefaults.cardColors(Color(0xff373737))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        value = todo,
                        onValueChange = { todo = it },
                        label = {
                            Text(" Add Task ",
                            color = Color.White)
                        },
                        singleLine = true,
                        modifier = Modifier
                            .padding(2.dp)
                            .focusRequester(focusRequester)
                    )

                    Icon(
                        painter = painterResource(R.drawable.cross),
                        contentDescription = "Cancel",
                        modifier = Modifier
                            .padding(4.dp)
                            .size(24.dp)
                            .clickable {
                                AddTaskClicked = false
                                todo = ""
                            },
                        tint = Color.Unspecified
                    )

                    Icon(
                        painter = painterResource(R.drawable.tick),
                        contentDescription = "Add",
                        modifier = Modifier
                            .padding(4.dp)
                            .size(24.dp)
                            .clickable {
                                AddTaskClicked = false
                                ToDos.add(todo)
                                todo = ""
                           },
                        tint = Color.Unspecified
                    )
                }
            }

            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
                keyboardController?.show()
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewToDo()
{
    ToDo()
}