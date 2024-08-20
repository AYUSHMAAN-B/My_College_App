package com.example.mycollege

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import com.example.mycollege.presentation.NavGraphs
import com.example.mycollege.presentation.theme.MyCollegeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCollegeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}

data class Slot(
    val id: Int,
    var courseID: Int?,
    val day : String,
    val startTime: String,
    val endTime: String,
)

val timetable = mutableStateListOf(
    Slot(id = 1, courseID = null, day = "Monday", startTime = "8:30", endTime = "9:30"),
    Slot(id = 2, courseID = null, day = "Monday", startTime = "9:30", endTime = "10:30"),
    Slot(id = 3, courseID = null, day = "Monday", startTime = "10:30", endTime = "11:30"),
    Slot(id = 4, courseID = null, day = "Monday", startTime = "11:30", endTime = "12:30"),
    Slot(id = 5, courseID = null, day = "Monday", startTime = "2:00", endTime = "3:00"),
    Slot(id = 6, courseID = null, day = "Monday", startTime = "3:00", endTime = "4:00"),
    Slot(id = 7, courseID = null, day = "Monday", startTime = "5:00", endTime = "6:00"),
    Slot(id = 8, courseID = null, day = "Tuesday", startTime = "8:30", endTime = "9:45"),
    Slot(id = 9, courseID = null, day = "Tuesday", startTime = "9:50", endTime = "11:05"),
    Slot(id = 10, courseID = null, day = "Tuesday", startTime = "11:10", endTime = "12:25"),
    Slot(id = 11, courseID = null, day = "Tuesday", startTime = "2:00", endTime = "3:15"),
    Slot(id = 12, courseID = null, day = "Tuesday", startTime = "3:20", endTime = "4:35"),
    Slot(id = 13, courseID = null, day = "Tuesday", startTime = "4:40", endTime = "6:00"),
    Slot(id = 14, courseID = null, day = "Wednesday", startTime = "8:30", endTime = "9:30"),
    Slot(id = 15, courseID = null, day = "Wednesday", startTime = "9:30", endTime = "10:30"),
    Slot(id = 16, courseID = null, day = "Wednesday", startTime = "10:30", endTime = "11:30"),
    Slot(id = 17, courseID = null, day = "Wednesday", startTime = "11:30", endTime = "12:30"),
    Slot(id = 18, courseID = null, day = "Wednesday", startTime = "2:00", endTime = "3:00"),
    Slot(id = 19, courseID = null, day = "Wednesday", startTime = "3:00", endTime = "4:00"),
    Slot(id = 20, courseID = null, day = "Wednesday", startTime = "5:00", endTime = "6:00"),
    Slot(id = 21, courseID = null, day = "Thursday", startTime = "8:30", endTime = "9:45"),
    Slot(id = 22, courseID = null, day = "Thursday", startTime = "9:50", endTime = "11:05"),
    Slot(id = 23, courseID = null, day = "Thursday", startTime = "11:10", endTime = "12:25"),
    Slot(id = 24, courseID = null, day = "Thursday", startTime = "2:00", endTime = "3:15"),
    Slot(id = 25, courseID = null, day = "Thursday", startTime = "3:20", endTime = "4:35"),
    Slot(id = 26, courseID = null, day = "Thursday", startTime = "4:40", endTime = "6:00"),
    Slot(id = 27, courseID = null, day = "Friday", startTime = "8:30", endTime = "9:30"),
    Slot(id = 28, courseID = null, day = "Friday", startTime = "9:30", endTime = "10:30"),
    Slot(id = 29, courseID = null, day = "Friday", startTime = "10:30", endTime = "11:30"),
    Slot(id = 30, courseID = null, day = "Friday", startTime = "11:30", endTime = "12:30"),
    Slot(id = 31, courseID = null, day = "Friday", startTime = "2:00", endTime = "3:00"),
    Slot(id = 32, courseID = null, day = "Friday", startTime = "3:00", endTime = "4:00"),
    Slot(id = 33, courseID = null, day = "Friday", startTime = "5:00", endTime = "6:00")
)