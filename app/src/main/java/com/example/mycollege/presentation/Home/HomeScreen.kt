package com.example.mycollege.presentation.Home

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycollege.presentation.destinations.AllCoursesScreenRouteDestination
import com.example.mycollege.presentation.destinations.GradesScreenRouteDestination
import com.example.mycollege.presentation.destinations.QuickLinksRouteDestination
import com.example.mycollege.presentation.destinations.ToDoScreenRouteDestination
import com.example.mycollege.presentation.theme.appBackgroundColor
import com.example.mycollege.presentation.theme.cardColor
import com.example.mycollege.presentation.theme.topAppBarColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreenRoute(
    navigator: DestinationsNavigator
) {
    HomeScreen(
        onSemesterClicked = {
            navigator.navigate(AllCoursesScreenRouteDestination)
        },
        onQuickLinksClicked = {
            navigator.navigate(QuickLinksRouteDestination)
        },
        onToDoClicked = {
            navigator.navigate(ToDoScreenRouteDestination)
        },
        onGradesClicked = {
            navigator.navigate(GradesScreenRouteDestination)
        },
        onTimeTableClicked = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    onSemesterClicked : () -> Unit,
    onQuickLinksClicked : () -> Unit,
    onToDoClicked : () -> Unit,
    onGradesClicked : () -> Unit,
    onTimeTableClicked : () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text = "Welcome Ayushmaan",
                    fontSize = 24.sp
                ) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = topAppBarColor
                )
            )
        },
    ) {
        Column(
            modifier = Modifier
                .background(appBackgroundColor)
                .padding(it)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 18.dp)
                    .width(200.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card (
                    modifier = Modifier
                        .size(130.dp)
                        .rotate(45f)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(cardColor, shape = RectangleShape)
                        .clickable { onGradesClicked() },
                    elevation = CardDefaults.cardElevation( defaultElevation = 10.dp ),
                    colors = CardDefaults.cardColors(
                        containerColor = cardColor,
                        contentColor = Color.White
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .rotate(-45f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Grades",
                            fontSize = 24.sp)
                    }
                }

                Card (
                    modifier = Modifier
                        .size(130.dp)
                        .rotate(45f)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(cardColor, shape = RectangleShape)
                        .clickable { onQuickLinksClicked() },
                    elevation = CardDefaults.cardElevation( defaultElevation = 10.dp ),
                    colors = CardDefaults.cardColors(
                        containerColor = cardColor,
                        contentColor = Color.White
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .rotate(-45f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Quick Links",
                            fontSize = 24.sp)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(200.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Card (
                    modifier = Modifier
                        .size(130.dp)
                        .rotate(45f)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(cardColor, shape = RectangleShape)
                        .clickable { onSemesterClicked() },
                    elevation = CardDefaults.cardElevation( defaultElevation = 10.dp ),
                    colors = CardDefaults.cardColors(
                        containerColor = cardColor,
                        contentColor = Color.White
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .rotate(-45f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Semester",
                            fontSize = 24.sp)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 18.dp)
                    .width(200.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card (
                    modifier = Modifier
                        .size(130.dp)
                        .rotate(45f)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(cardColor, shape = RectangleShape)
                        .clickable { onTimeTableClicked() },
                    elevation = CardDefaults.cardElevation( defaultElevation = 10.dp ),
                    colors = CardDefaults.cardColors(
                        containerColor = cardColor,
                        contentColor = Color.White
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .rotate(-45f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("TimeTable",
                            fontSize = 24.sp)
                    }
                }

                Card (
                    modifier = Modifier
                        .size(130.dp)
                        .rotate(45f)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(cardColor, shape = RectangleShape)
                        .clickable { onToDoClicked() },
                    elevation = CardDefaults.cardElevation( defaultElevation = 10.dp ),
                    colors = CardDefaults.cardColors(
                        containerColor = cardColor,
                        contentColor = Color.White
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .rotate(-45f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("ToDo",
                            fontSize = 24.sp)
                    }
                }
            }
        }
    }
}