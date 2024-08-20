package com.example.mycollege.di

import android.app.Application
import androidx.room.Room
import com.example.mycollege.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule
{
    @Provides
    @Singleton
    fun providesDatabase(application: Application): AppDatabase
    {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "mycollege.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesCourseDao(database: AppDatabase) = database.courseDao()

    @Provides
    @Singleton
    fun providesGradesDao(database: AppDatabase) = database.gradesDao()

    @Provides
    @Singleton
    fun providesMarksDao(database: AppDatabase) = database.marksDao()

    @Provides
    @Singleton
    fun providesTaskDao(database: AppDatabase) = database.tasksDao()

    @Provides
    @Singleton
    fun providesToDoDao(database: AppDatabase) = database.todoDao()
}