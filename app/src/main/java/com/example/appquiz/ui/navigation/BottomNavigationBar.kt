package com.example.appquiz.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(Destinations.Home)

    NavigationBar(
        containerColor = Color(0xFF5800FF), // color de fondo
        contentColor = Color.White // color de contenido (iconos y texto)
    ) {
        val currentRoute = navController.currentDestination?.route
        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        screen.icon,
                        contentDescription = screen.title,
                        tint = if (currentRoute == screen.route) Color.Black else Color.White // cambia color del icono seleccionado
                    )
                },
                label = {
                    Text(
                        screen.title,
                        color = if (currentRoute == screen.route) Color.Black else Color.White
                    )
                }
            )
        }
    }
}
