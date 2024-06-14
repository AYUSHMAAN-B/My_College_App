package com.example.grades.Grade_Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grades.Data.Course

@Composable
fun SPI_CPI(
    courses: List<Course>,
    sem: Int,
    credits : Array<Int>,
    spi: Array<Double>,
    cpi: Array<Double>
) {
    var totalCredits = 0
    var totalGrades = 0

    courses.forEach {
        if (it.sem == sem)
        {
            totalCredits += it.credits
            totalGrades += it.credits * it.grade
        }
    }

    spi[sem - 1] = totalGrades.toDouble() / totalCredits

    if( sem >= 2 )
    {
        cpi[sem-1] = (cpi[sem-2]*credits[sem-2] + totalGrades) / (credits[sem-2] + totalCredits).toDouble()
        credits[sem-1] = credits[sem-2] + totalCredits
    }
    else
    {
        cpi[0] = spi[0]
        credits[0] = totalCredits
    }



    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .padding(2.dp)
                .clip(RoundedCornerShape(5.dp))
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(Color(0xff8ecae6))
                    .padding(8.dp)
            ) {
                Text(
                    text = "SPI : ${String.format("%.2f", spi[sem-1])}",
                    color = Color.Black,
                    fontSize = 20.sp
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
                    .background(Color(0xff8ecae6))
                    .padding(8.dp)
            ) {
                Text(
                    text = "CPI : ${String.format("%.2f", cpi[sem-1])}",
                    color = Color.Black,
                    fontSize = 20.sp
                )
            }
        }
    }
}
