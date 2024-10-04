package com.mlv.prisustva.data

import kotlinx.coroutines.flow.Flow

class StudentRepository(private val studentDao: StudentDao) {

    // Get all students as a Flow
    fun getAllStudents(): Flow<List<Student>> = studentDao.getAllStudents()

    // Add a new student
    suspend fun addStudent(student: Student): Long {
        return studentDao.insertStudent(student)
    }

    // Update student details
    suspend fun updateStudent(student: Student) {
        studentDao.updateStudent(student)
    }

    // Delete a student by object
    suspend fun deleteStudent(student: Student) {
        studentDao.deleteStudent(student)
    }

    // Delete a student by ID
    suspend fun deleteStudentById(id: Int): Int {
        return studentDao.deleteStudentById(id)
    }

    // Get a student by ID as a Flow
    fun getStudentById(id: Int): Flow<Student?> {
        return studentDao.getStudentById(id)
    }
}
