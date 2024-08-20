package com.example.mycollege.data.repoImpl

import com.example.mycollege.data.dao.MarksDao
import com.example.mycollege.data.model.Marks
import com.example.mycollege.data.repo.MarksRepository
import javax.inject.Inject

class MarksRepoImpl @Inject constructor(
    private val marksDao: MarksDao
) : MarksRepository {
    override suspend fun upsertMarks(marks: Marks) = marksDao.insertMarks(marks)
    override suspend fun deleteMark(id: Int) = marksDao.deleteMarks(id)
    override fun getAllMarksByCourseId(courseID: Int) = marksDao.getAllMarksByCourseId(courseID)
    override suspend fun getMarksByCourseIdAndId(courseID: Int, id: Int) = marksDao.getMarksByCourseIdAndId(courseID, id)
}