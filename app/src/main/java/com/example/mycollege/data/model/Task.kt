package com.example.mycollege.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val courseID : Int,
    var name : String,
    var dueDate : Long,
    var isCompleted : Boolean
)
