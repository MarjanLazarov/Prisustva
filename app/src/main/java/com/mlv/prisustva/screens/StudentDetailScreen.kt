package com.mlv.prisustva.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mlv.prisustva.ui.StudentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDetailScreen(navController: NavController, studentId: Int, studentViewModel: StudentViewModel) {
    // Load the student when the screen is created
    LaunchedEffect(studentId) {
        studentViewModel.loadStudentById(studentId)
    }

    // Observe the student details
    val student by studentViewModel.studentDetail.collectAsState()

    val context = LocalContext.current

    // Ensure we have a valid student before rendering the UI
    student?.let {
        var name by remember { mutableStateOf(it.name) }
        var attendance by remember { mutableStateOf(it.attendance) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Student Details") },
                    actions = {
                        IconButton(onClick = {
                            if (name.isNotBlank()) {
                                studentViewModel.updateStudent(it.copy(name = name, attendance = attendance))
                                Toast.makeText(context, "Student updated successfully", Toast.LENGTH_SHORT).show()
                                navController.popBackStack() // Navigate back to list screen
                            }
                        }) {
                            Icon(Icons.Default.Check, contentDescription = "Save Student")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = attendance,
                        onCheckedChange = { attendance = it }
                    )
                    Text(text = if (attendance) "Present" else "Absent")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (name.isNotBlank()) {
                            studentViewModel.updateStudent(it.copy(name = name, attendance = attendance))
                            Toast.makeText(context, "Student updated successfully", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Changes")
                }
            }
        }
    }
}