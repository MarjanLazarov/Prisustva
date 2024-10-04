package com.mlv.prisustva.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlv.prisustva.data.Student
import com.mlv.prisustva.data.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StudentViewModel(private val studentRepository: StudentRepository) : ViewModel() {

    private val _studentList = MutableStateFlow<List<Student>>(emptyList())
    val studentList: StateFlow<List<Student>> get() = _studentList

    private val _studentDetail = MutableStateFlow<Student?>(null)
    val studentDetail: StateFlow<Student?> get() = _studentDetail

    init {
        // Observe the list of students
        viewModelScope.launch {
            studentRepository.getAllStudents().collect { students ->
                _studentList.value = students
            }
        }
    }

    // Load a single student by ID
    fun loadStudentById(id: Int) {
        viewModelScope.launch {
            // Collect the Flow and update _studentDetail
            studentRepository.getStudentById(id).collect { student ->
                _studentDetail.value = student
            }
        }
    }

    // Add a new student
    fun addStudent(student: Student) {
        viewModelScope.launch {
            studentRepository.addStudent(student)
        }
    }

    // Update an existing student
    fun updateStudent(student: Student) {
        viewModelScope.launch {
            studentRepository.updateStudent(student)
        }
    }

    // Delete a student by object
    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            studentRepository.deleteStudent(student)
        }
    }

    // Delete a student by ID
    fun deleteStudentById(id: Int) {
        viewModelScope.launch {
            studentRepository.deleteStudentById(id)
        }
    }
}
