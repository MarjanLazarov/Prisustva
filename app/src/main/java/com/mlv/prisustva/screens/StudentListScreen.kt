package com.mlv.prisustva.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mlv.prisustva.data.Student
import com.mlv.prisustva.ui.StudentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen(navController: NavController, studentViewModel: StudentViewModel) {
    val students by studentViewModel.studentList.collectAsState() // Observe the student list from Flow

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Student List") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("add_student") // Navigate to add screen
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Student")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(students) { student ->
                StudentItem(
                    student = student,
                    onClick = {
                        // Navigate to the student detail screen with ID
                        navController.navigate("student_detail/${student.id}")
                    },
                    onDelete = {
                        studentViewModel.deleteStudent(student) // Delete student action
                    }
                )
            }
        }
    }
}

@Composable
fun StudentItem(student: Student, onClick: () -> Unit, onDelete: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    onDelete()
                    showDialog = false
                }) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            },
            title = { Text("Delete Student") },
            text = { Text("Are you sure you want to delete this student?") }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Name: ${student.name}",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "ID: ${student.id}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = if (student.attendance) "Status: Present" else "Status: Absent",
                style = MaterialTheme.typography.bodyMedium
            )

            // Delete Button
            IconButton(onClick = { showDialog = true }, modifier = Modifier.align(Alignment.End)) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Student")
            }
        }
    }
}