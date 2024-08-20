package com.example.mycollege.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Grades(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val name : String,
    val credits : Int,
    val grade : Int,
    val sem : Int
)

