package com.mlv.prisustva.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    // Observe a student by ID using Flow
    @Query("SELECT * FROM students WHERE id = :studentId LIMIT 1")
    fun getStudentById(studentId: Int): Flow<Student?>

    // Get all students as a Flow
    @Query("SELECT * FROM students")
    fun getAllStudents(): Flow<List<Student>>

    // Insert a new student and return the row ID
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student): Long

    // Update a student and return the number of affected rows
    @Update
    suspend fun updateStudent(student: Student): Int

    // Delete a student by object and return the number of affected rows
    @Delete
    suspend fun deleteStudent(student: Student): Int

    // Delete a student by ID and return the number of affected rows
    @Query("DELETE FROM students WHERE id = :studentId")
    suspend fun deleteStudentById(studentId: Int): Int
}
