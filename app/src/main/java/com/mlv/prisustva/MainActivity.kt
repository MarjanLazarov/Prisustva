package com.mlv.prisustva

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mlv.prisustva.data.AppDatabase
import com.mlv.prisustva.data.StudentRepository
import com.mlv.prisustva.factory.StudentViewModelFactory
import com.mlv.prisustva.screens.AddStudentScreen
import com.mlv.prisustva.screens.StudentDetailScreen
import com.mlv.prisustva.screens.StudentListScreen
import com.mlv.prisustva.ui.StudentViewModel
import com.mlv.prisustva.ui.theme.PrisustvaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the database and repository
        val database = AppDatabase.getDatabase(this)
        val studentRepository = StudentRepository(database.studentDao())
        val studentViewModel: StudentViewModel by viewModels {
            StudentViewModelFactory(studentRepository)
        }

        setContent {
            PrisustvaTheme {
                val navController: NavHostController = rememberNavController()

                // Define the navigation graph
                NavHost(
                    navController = navController,
                    startDestination = "student_list"
                ) {
                    composable("student_list") {
                        StudentListScreen(navController = navController, studentViewModel = studentViewModel)
                    }
                    composable("add_student") {
                        AddStudentScreen(navController = navController, studentViewModel = studentViewModel)
                    }
                    composable("student_detail/{studentId}") { backStackEntry ->
                        val studentId = backStackEntry.arguments?.getString("studentId")?.toIntOrNull()
                        if (studentId != null) {
                            StudentDetailScreen(navController = navController, studentId = studentId, studentViewModel = studentViewModel)
                        }
                    }
                }
            }
        }
    }
}
