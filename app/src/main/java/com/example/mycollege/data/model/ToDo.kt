package com.example.mycollege.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val name : String
)
