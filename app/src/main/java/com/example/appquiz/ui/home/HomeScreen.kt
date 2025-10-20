package com.example.appquiz.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.appquiz.data.model.Quiz
import com.example.appquiz.ui.navigation.Destinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    padding: PaddingValues,
    viewModel: HomeViewModel = viewModel()
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Lista de Quizzes") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF5800FF),
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
                //.background(Color(0xFFB49AE7)),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            items(viewModel.quizzes) { quiz ->
                QuizCard(
                    quiz = quiz,
                    onClick = {
                        navController.navigate(Destinations.QuizScreen.createRoute(quiz.id))
                    }
                )
            }
        }
    }
}

@Composable
fun QuizCard(
    quiz: Quiz,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp, 16.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE7E7E7)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = quiz.titulo,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            /*if (mostrarPreguntas) {
                quiz.preguntas.forEachIndexed { index, opcion ->
                    Text(
                        text = "${index + 1}. $opcion",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
            } else {
                // Muestra solo un resumen (por ejemplo, las dos primeras)
                quiz.preguntas.take(2).forEachIndexed { index, opcion ->
                    Text(
                        text = "${index + 1}. $opcion",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
                Text(
                    text = "Ver más...",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }*/
        }
    }
}

