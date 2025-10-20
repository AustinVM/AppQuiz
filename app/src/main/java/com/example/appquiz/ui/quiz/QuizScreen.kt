package com.example.appquiz.ui.quiz

import androidx.benchmark.traceprocessor.Row
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.appquiz.data.model.Quiz

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    navController: NavController,
    quizId: Int,
    paddingValues: PaddingValues,
    viewModel: QuizViewModel = viewModel()
) {
    val quiz = viewModel.quizzes.find { it.id == quizId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(quiz?.titulo ?: "Quiz") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF5800FF),
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->
        quiz?.let { q ->
            var quizIniciado by remember { mutableStateOf(false) }
            var quizFinalizado by remember { mutableStateOf(false) }
            var puntaje by remember { mutableStateOf(0) }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Card principal
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(6.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = q.titulo,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Este quiz contiene ${q.preguntas.size} preguntas.",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }

                // Cards secundarias (una por pregunta)
                if (quizIniciado) {
                    items(q.preguntas) { pregunta ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = pregunta.texto,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                                pregunta.opciones.forEachIndexed { index, opcion ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        RadioButton(
                                            selected = pregunta.respuestaUsuario == index,
                                            onClick = {
                                                viewModel.registrarRespuesta(q.id, pregunta.id, index)
                                            }
                                        )
                                        Text(
                                            text = opcion,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Botón de iniciar/finalizar
                item {
                    Button(
                        onClick = {
                            if (!quizIniciado) {
                                quizIniciado = true
                            } else if (!quizFinalizado) {
                                quizFinalizado = true
                                puntaje = viewModel.calcularPuntaje(q)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5800FF),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = when {
                                !quizIniciado -> "Comenzar Quiz"
                                !quizFinalizado -> "Finalizar Quiz"
                                else -> "Puntaje: $puntaje/${q.preguntas.size}"
                            }
                        )
                    }
                }
            }
        } ?: run {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Quiz no encontrado")
            }
        }
    }
}
