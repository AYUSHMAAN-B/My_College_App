package com.example.mycollege.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Marks(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val courseID : Int,
    var name : String,
    var got : Double,
    var outOf : Double,
    var weightage : Double,
    var total : Double
)

