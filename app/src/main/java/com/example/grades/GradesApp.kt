package com.example.grades

import android.app.Application

class GradesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}