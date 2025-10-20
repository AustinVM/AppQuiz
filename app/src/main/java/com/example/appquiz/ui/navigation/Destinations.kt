package com.example.appquiz.ui.navigation

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(val route: String, val icon: ImageVector, val title: String) {
    object Home : Destinations("home", Icons.Default.Home, "Pág. Principal")
    object QuizScreen : Destinations("quizScreen/{quizId}", Icons.Default.Home,"Detalle de Producto") {
        fun createRoute(quizId: Int) = "quizScreen/$quizId"
    }
    /*object QuizScreen : Destinations("quiz", Icons.Default.Add,"Galeria")
    object Audio : Destinations("audio", Icons.Default.PermMedia,"Audio")
    object ProductDetail : Destinations("productDetail/{productId}", Icons.Default.Shop,"Detalle de Producto") {
        fun createRoute(productId: Int) = "productDetail/$productId"
    }
    object PetItem : Destinations("petItem/{petId}", Icons.Default.CatchingPokemon,"Detalle de Mascota") {
        fun createRoute(petId: Int) = "petItem/$petId"
    }*/
}