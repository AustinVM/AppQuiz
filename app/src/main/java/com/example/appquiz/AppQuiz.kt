package com.example.appquiz

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.appquiz.ui.navigation.BottomNavigationBar
import com.example.appquiz.ui.navigation.NavGraph

@Composable
fun AppQuiz() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController) // Tu barra inferior global
        },
        contentColor = Color(0xFFB49AE7)
    ) { innerPadding ->
        NavGraph(
            navController = navController,
            padding = innerPadding // 👈 se pasa a cada pantalla
        )
    }
}
