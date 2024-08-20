package com.example.mycollege.di

import com.example.mycollege.data.repo.CourseRepository
import com.example.mycollege.data.repo.GradesRepository
import com.example.mycollege.data.repo.MarksRepository
import com.example.mycollege.data.repo.TasksRepository
import com.example.mycollege.data.repo.ToDoRepository
import com.example.mycollege.data.repoImpl.CourseRepoImpl
import com.example.mycollege.data.repoImpl.GradesRepoImpl
import com.example.mycollege.data.repoImpl.MarksRepoImpl
import com.example.mycollege.data.repoImpl.TasksRepoImpl
import com.example.mycollege.data.repoImpl.ToDoRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule
{
    @Binds
    @Singleton
    abstract fun bindCourseRepository(
        impl: CourseRepoImpl
    ): CourseRepository

    @Binds
    @Singleton
    abstract fun bindGradesRepository(
        impl : GradesRepoImpl
    ): GradesRepository

    @Binds
    @Singleton
    abstract fun bindTaskRepository(
        impl: TasksRepoImpl
    ): TasksRepository

    @Binds
    @Singleton
    abstract fun bindMarksRepository(
        impl: MarksRepoImpl
    ): MarksRepository

    @Binds
    @Singleton
    abstract fun bindToDoRepository(
        impl : ToDoRepoImpl
    ): ToDoRepository
}