package com.example.grades.QuickLinks_Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.grades.ui.theme.overallBackground

@Composable
fun QuickLinks(
    navController: NavController
)  {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(overallBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val calendarUrl = "calendar-0"

        Card(
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(start = 32.dp, top = 8.dp)
                .size(width = 175.dp, height = 65.dp)
                .clickable { navController.navigate("webscreen/$calendarUrl") },
            colors = CardDefaults.cardColors(Color(0xff001d3d))
        ) {
            Text(
                text = "Calendar",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )
        }

        val circularURL = "circular"

        Card(
            modifier = Modifier
                .align(alignment = Alignment.End)
                .padding(end = 32.dp, top = 8.dp)
                .size(width = 175.dp, height = 65.dp)
                .clickable { navController.navigate("webscreen/$circularURL") },
            colors = CardDefaults.cardColors(Color(0xff001d3d))
        ) {
            Text(
                text = "Circular",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )
        }

        val curriculumURL = "curriculum"

        Card(
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(start = 32.dp, top = 8.dp)
                .size(width = 175.dp, height = 65.dp)
                .clickable { navController.navigate("webscreen/$curriculumURL") },
            colors = CardDefaults.cardColors(Color(0xff001d3d))
        ) {
            Text(
                text = "Curriculum",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )
        }

        val examsURL = "exams"

        Card(
            modifier = Modifier
                .align(alignment = Alignment.End)
                .padding(end = 32.dp, top = 8.dp)
                .size(width = 175.dp, height = 65.dp)
                .clickable { navController.navigate("webscreen/$examsURL") },
            colors = CardDefaults.cardColors(Color(0xff001d3d))
        ) {
            Text(
                text = "Exams",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )
        }

        val holidaysURL = "holidays"

        Card(
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(start = 32.dp, top = 8.dp)
                .size(width = 175.dp, height = 65.dp)
                .clickable { navController.navigate("webscreen/$holidaysURL") },
            colors = CardDefaults.cardColors(Color(0xff001d3d))
        ) {
            Text(
                text = "Holidays",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )
        }

        val timeTableURL = "timetable"

        Card(
            modifier = Modifier
                .align(alignment = Alignment.End)
                .padding(end = 32.dp, top = 8.dp)
                .size(width = 175.dp, height = 65.dp)
                .clickable { navController.navigate("webscreen/$timeTableURL") },
            colors = CardDefaults.cardColors(Color(0xff001d3d))
        ) {
            Text(
                text = "Time Table",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )
        }
    }
}