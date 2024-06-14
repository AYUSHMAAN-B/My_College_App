package com.example.grades.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_table")
data class Course(
    @PrimaryKey
    val id : Int = 0,
    @ColumnInfo(name = "course_name")
    val name : String = "",
    @ColumnInfo(name = "course_code")
    val credits : Int = 0,
    @ColumnInfo(name = "course_grade")
    val grade : Int = 0,
    @ColumnInfo(name = "course_sem")
    val sem : Int = 0
)
