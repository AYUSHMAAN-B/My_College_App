package com.example.grades.TimeTable_Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grades.ui.theme.C
import com.example.grades.ui.theme.CLab
import com.example.grades.ui.theme.CN
import com.example.grades.ui.theme.CNLab
import com.example.grades.ui.theme.Days
import com.example.grades.ui.theme.HSS
import com.example.grades.ui.theme.OS
import com.example.grades.ui.theme.OSLab
import com.example.grades.ui.theme.Timings
import com.example.grades.ui.theme.overallBackground

@Composable
fun TimeTable() {

    val timetable = listOf(
        listOf("", "8:30 - 9:30", "9:30 - 10:30", "10:30 - 11:30", "11:30 - 12:30", "2:00 - 3:00", "3:00 - 4:00", "4:00 - 5:00", "5:00 - 6:00" ),
        listOf("Monday", "CN", "", "OS", "Compilers", "", "", "", ""),
        listOf("Tuesday", "C Lab", "C Lab", "HSS", "", "", "CN Lab", "CN Lab", "CN Lab"),
        listOf("Wednesday", "Compilers", "CN", "", "OS", "", "", "", ""),
        listOf("Thursday", "", "HSS", "C Lab", "C Lab", "", "OS Lab", "OS Lab", "OS Lab"),
        listOf("Friday", "OS", "Compilers", "CN", "", "", "", "", ""),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(overallBackground)
            .padding(8.dp)
    ) {
        item {
            Row(modifier = Modifier
                .fillMaxSize()
            ) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(timetable) { row ->
                        Column {
                            row.forEach { subject ->
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .background(courseColor(subject))
                                        .border(2.dp, overallBackground)
                                ) {
                                    Text(text = subject,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun courseColor(subject: String): Color
{
    return when(subject) {
        "CN" -> CN
        "CN Lab" -> CNLab
        "OS" -> OS
        "OS Lab" -> OSLab
        "Compilers" -> C
        "C Lab" -> CLab
        "HSS" -> HSS
        "Monday" -> Days
        "Tuesday" -> Days
        "Wednesday" -> Days
        "Thursday" -> Days
        "Friday" -> Days
        "8:30 - 9:30", "9:30 - 10:30", "10:30 - 11:30", "11:30 - 12:30", "2:00 - 3:00", "3:00 - 4:00", "4:00 - 5:00", "5:00 - 6:00" -> Timings
        "" -> overallBackground
        else -> Color.White
    }
}

@Preview(showBackground = true)
@Composable
fun TimeTablePreview()
{
    TimeTable()
}

