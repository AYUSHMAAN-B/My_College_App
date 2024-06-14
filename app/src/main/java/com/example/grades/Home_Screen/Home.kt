package com.example.grades.Home_Screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grades.R
import com.example.grades.ui.theme.overallBackground

@Composable
fun Home(
    onGradesClicked : () -> Unit,
    onQuickLinksClicked : () -> Unit,
    onTimeTableClicked : () -> Unit,
    onToDoClicked : () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(overallBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(overallBackground)
                .padding(16.dp)
                .border(2.dp, color = Color(0xff023047))
        ) {
            Text("IIT DHARWAD", color = Color.Black, fontSize = 32.sp,
                modifier = Modifier.padding(16.dp))
        }

        Image(
            painter = painterResource(id = R.drawable.emblem),
            contentDescription = null,
            modifier = Modifier.padding(16.dp).size(150.dp)
        )

        Column(modifier = Modifier
            .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Card (
                    modifier = Modifier
                        .padding(16.dp)
                        .size(150.dp)
                        .clickable { onGradesClicked() },
                    border = BorderStroke(2.dp, color = Color.White)
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xff023047)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Grades", color = Color.White, fontSize = 24.sp)
                    }
                }

                Card (
                    modifier = Modifier
                        .padding(16.dp)
                        .size(150.dp)
                        .clickable { onQuickLinksClicked() },
                    border = BorderStroke(2.dp, color = Color.White)
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xff023047)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Quick Links", color = Color.White, fontSize = 24.sp)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Card (
                    modifier = Modifier
                        .padding(16.dp)
                        .size(150.dp)
                        .clickable { onTimeTableClicked() },
                    border = BorderStroke(2.dp, color = Color.White)
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xff023047)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Time Table", color = Color.White, fontSize = 24.sp)
                    }
                }

                Card (
                    modifier = Modifier
                        .padding(16.dp)
                        .size(150.dp)
                        .clickable { onToDoClicked() },
                    border = BorderStroke(2.dp, color = Color.White)
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xff023047)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("ToDo", color = Color.White, fontSize = 24.sp)
                    }
                }
            }
        }
    }
}