package com.example.appquiz.ui.home

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.appquiz.data.model.Quiz
import com.example.appquiz.ui.quiz.QuizViewModel

/*class HomeViewModel : ViewModel() {
    // Lista observable para que Compose se actualice automáticamente
    val quizzes: SnapshotStateList<Quiz> = mutableStateListOf(
        Quiz(
            id = 1,
            texto = "Quiz de Historia",
            opciones = listOf(
                "Pregunta 1",
                "Pregunta 2",
                "Pregunta 3"
            ),
            indiceCorrecto = null,
            respuestaUsuario = null
        ),
        Quiz(
            id = 2,
            texto = "Quiz de Deportes",
            opciones = listOf(
                "Pregunta 1",
                "Pregunta 2",
                "Pregunta 3"
            ),
            indiceCorrecto = null,
            respuestaUsuario = null
        ),
        Quiz(
            id = 3,
            texto = "Quiz de Matemáticas",
            opciones = listOf(
                "Pregunta 1",
                "Pregunta 2",
                "Pregunta 3"
            ),
            indiceCorrecto = null,
            respuestaUsuario = null
        )
    )
}*/

class HomeViewModel : QuizViewModel()