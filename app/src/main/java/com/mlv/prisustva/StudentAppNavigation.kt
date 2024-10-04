package com.mlv.prisustva

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mlv.prisustva.data.StudentRepository
import com.mlv.prisustva.factory.StudentViewModelFactory
import com.mlv.prisustva.screens.AddStudentScreen
import com.mlv.prisustva.screens.MainScreen
import com.mlv.prisustva.screens.StudentListScreen
import com.mlv.prisustva.ui.StudentViewModel

@Composable
fun StudentAppNavigation(studentRepository: StudentRepository) {
    val navController: NavHostController = rememberNavController()

    // Create the ViewModel using the factory
    val studentViewModel: StudentViewModel = viewModel(
        factory = StudentViewModelFactory(studentRepository)
    )

    NavHost(navController = navController, startDestination = "main_screen") {
        // Main Screen route
        composable("main_screen") {
            MainScreen(navController)
        }
        // Student List Screen route
        composable("student_list") {
            StudentListScreen(navController, studentViewModel)
        }
        // Add/Edit Student Screen route
        composable("add_student") {
            AddStudentScreen(navController, studentViewModel)
        }
    }
}