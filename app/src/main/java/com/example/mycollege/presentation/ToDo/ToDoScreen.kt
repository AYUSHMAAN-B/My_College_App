package com.example.mycollege.presentation.ToDo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycollege.R
import com.example.mycollege.data.model.ToDo
import com.example.mycollege.presentation.theme.appBackgroundColor
import com.example.mycollege.presentation.theme.cardColor
import com.example.mycollege.presentation.theme.topAppBarColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@Destination
@Composable
fun ToDoScreenRoute(
    navigator: DestinationsNavigator
) {
    val viewModel : ToDoViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()

    ToDoScreen(
        navigator = navigator,
        state = state.value,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ToDoScreen(
    navigator: DestinationsNavigator,
    state : ToDoScreenState,
    onEvent : (ToDoScreenEvent) -> Unit
) {
    var addTaskClicked by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("To-Do List",
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
        }
    ) { paddingValues ->
        Box {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .background(appBackgroundColor)
                    .background(appBackgroundColor)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn {
                    items(
                        items = state.todos,
                        key = { todo -> todo.id ?: todo.name.hashCode() }
                    ) { todo ->
                        SwipeToDeleteContainer(
                            item = todo,
                            onDelete = {
                                todo.id?.let { id -> onEvent(ToDoScreenEvent.OnDeleteToDo(id)) }
                            }
                        ) { todo ->
                            Card(
                                modifier = Modifier
                                    .width(500.dp)
                                    .height(60.dp)
                                    .padding(8.dp),
                                shape = MaterialTheme.shapes.extraSmall,
                                colors = CardDefaults.cardColors(
                                    containerColor = cardColor,
                                    contentColor = Color.White
                                )
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = todo.name,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }

            if (!addTaskClicked) {
                OutlinedButton(
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = cardColor
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(8.dp)
                        .height(40.dp)
                        .clip(shape = MaterialTheme.shapes.extraLarge),
                    onClick = { addTaskClicked = true }
                ) {
                    Text(
                        "Add a Task",
                        color = Color.White
                    )
                }
            }

            if (addTaskClicked) {
                Card(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(75.dp),
                    shape = MaterialTheme.shapes.extraSmall,
                    colors = CardDefaults.cardColors(cardColor)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        OutlinedTextField(
                            value = state.name,
                            onValueChange = {
                                    onEvent(ToDoScreenEvent.OnNameChange(it))
                            },
                            label = {
                                Text(" Add Task ",
                                    color = Color.White)
                            },
                            singleLine = true,
                            modifier = Modifier
                                .padding(2.dp)
                                .focusRequester(focusRequester),
                            textStyle = TextStyle(Color.White),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                cursorColor = Color.White,
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White
                            ),
                        )

                        Icon(
                            painter = painterResource(R.drawable.cancel),
                            contentDescription = "Cancel",
                            modifier = Modifier
                                .padding(4.dp)
                                .size(24.dp)
                                .clickable {
                                    addTaskClicked = false
                                    onEvent(ToDoScreenEvent.OnCancelToDo)
                                },
                            tint = Color.White
                        )

                        Icon(
                            painter = painterResource(R.drawable.save),
                            contentDescription = "Add",
                            modifier = Modifier
                                .padding(4.dp)
                                .size(24.dp)
                                .clickable {
                                    addTaskClicked = false
                                    onEvent(ToDoScreenEvent.OnInsertToDo)
                                },
                            tint = Color.White
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SwipeToDeleteContainer(
    item: T,
    onDelete: (T) -> Unit,
    animationDuration: Int = 500,
    content: @Composable (T) -> Unit
) {
    var isRemoved by remember {
        mutableStateOf(false)
    }
    val state = rememberDismissState(
        confirmValueChange = { value ->
            if (value == DismissValue.DismissedToStart) {
                isRemoved = true
                true
            } else {
                false
            }
        }
    )

    LaunchedEffect(key1 = isRemoved) {
        if(isRemoved) {
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismiss(
            state = state,
            background = {
                DeleteBackground(swipeDismissState = state)
            },
            dismissContent = { content(item) },
            directions = setOf(DismissDirection.EndToStart)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackground(
    swipeDismissState: DismissState
) {
    val color = if (swipeDismissState.dismissDirection == DismissDirection.EndToStart) {
        Color.Red
    } else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = Color.White
        )
    }
}