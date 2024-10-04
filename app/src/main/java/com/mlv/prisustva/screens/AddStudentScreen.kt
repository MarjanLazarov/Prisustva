package com.mlv.prisustva.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mlv.prisustva.data.Student
import com.mlv.prisustva.ui.StudentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStudentScreen(navController: NavController, studentViewModel: StudentViewModel) {
    var name by remember { mutableStateOf("") }
    var attendance by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Student") },
                actions = {
                    IconButton(onClick = {
                        if (name.isNotBlank()) {
                            studentViewModel.addStudent(Student(name = name, attendance = attendance))
                            Toast.makeText(context, "Student added successfully", Toast.LENGTH_SHORT).show()
                            navController.popBackStack() // Navigate back to list screen
                        } else {
                            Toast.makeText(context, "Name cannot be empty", Toast.LENGTH_SHORT).show()
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
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = if (attendance) "Present" else "Absent")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        studentViewModel.addStudent(Student(name = name, attendance = attendance))
                        Toast.makeText(context, "Student added successfully", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                    else {
                        Toast.makeText(context, "Name cannot be empty", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Student")
            }
        }
    }
}
