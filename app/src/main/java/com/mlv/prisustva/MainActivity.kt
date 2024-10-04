package com.mlv.prisustva

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mlv.prisustva.data.AppDatabase
import com.mlv.prisustva.data.StudentRepository
import com.mlv.prisustva.ui.theme.PrisustvaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val database = AppDatabase.getDatabase(this)
            val studentRepository = StudentRepository(database.studentDao())

            PrisustvaTheme {
                // Start the app with the navigation structure
                StudentAppNavigation(studentRepository)
            }
        }
    }
}
