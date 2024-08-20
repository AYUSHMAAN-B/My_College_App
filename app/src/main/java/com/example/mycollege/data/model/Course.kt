package com.example.mycollege.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    var name : String,
    var credits : Int,
    var targetGrade : Int?,
    var classroom : String?,
    var instructor : String?
)
