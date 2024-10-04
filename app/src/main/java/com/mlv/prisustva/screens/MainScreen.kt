package com.mlv.prisustva.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mlv.prisustva.R

@Composable
fun MainScreen (navController: NavController) {
    // Layout for the Main Screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display an Image
        Image(
            painter = painterResource(id = R.drawable.logo_bez),
            contentDescription = "Main Screen Image",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 32.dp)
        )

        // Button to navigate to Student List Screen
        Button(
            onClick = { navController.navigate("student_list") },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Go to Student List")
        }
    }
}