package com.example.appquiz.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.appquiz.ui.home.HomeScreen
import com.example.appquiz.ui.quiz.QuizScreen


@Composable
fun NavGraph(navController: NavHostController, padding: PaddingValues) {
    NavHost(navController, startDestination = Destinations.Home.route) {

        composable(Destinations.Home.route) {
            HomeScreen(navController, padding)
        }

        composable(
            route = Destinations.QuizScreen.route,
            arguments = listOf(navArgument("quizId") { type = NavType.IntType })
        ) { backStackEntry ->
            val quizId = backStackEntry.arguments?.getInt("quizId") ?: 0
            QuizScreen(
                navController = navController,
                quizId = quizId,
                paddingValues = padding
            )
        }

        /*composable(Destinations.QuizScreen.route) {
            QuizScreen(navController, padding)
        }

        composable(Destinations.Audio.route) {
            MediaScreen(navController, padding)
        }

        composable(
            route = Destinations.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailScreen(
                navController = navController,
                productId = productId,
                paddingValues = padding
            )
        }

        composable(
            route = Destinations.PetItem.route,
            arguments = listOf(navArgument("petId") { type = NavType.IntType })
        ) { backStackEntry ->
            val petId = backStackEntry.arguments?.getInt("petId") ?: 0
            PetItem(
                navController = navController,
                paddingValues = padding,
                petId = petId
            )
        } */
    }
}
