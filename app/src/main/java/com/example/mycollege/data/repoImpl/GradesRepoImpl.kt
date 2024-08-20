package com.example.mycollege.data.repoImpl

import com.example.mycollege.data.dao.GradesDao
import com.example.mycollege.data.model.Grades
import com.example.mycollege.data.repo.GradesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GradesRepoImpl @Inject constructor(
    private val gradesDao: GradesDao
) : GradesRepository {
    override fun getAllGrades(): Flow<List<Grades>> = gradesDao.getAllGrades()
    override suspend fun getGradeById(gradesID: Int) : Grades = gradesDao.getGradeById(gradesID)
    override suspend fun upsertGrade(grade: Grades) = gradesDao.upsertGrade(grade)
    override suspend fun deleteGrade(id: Int) = gradesDao.deleteGrade(id)
}