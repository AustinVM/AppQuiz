package com.example.appquiz.ui.quiz

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.appquiz.data.model.Quiz
import com.example.appquiz.data.model.Pregunta

open class QuizViewModel : ViewModel() {

    var quizzes = mutableStateListOf<Quiz>()
        private set

    init {
        quizzes.addAll(
            listOf(
                Quiz(
                    id = 1,
                    titulo = "Historia Universal",
                    preguntas = listOf(
                        Pregunta(
                            id = 1,
                            texto = "¿En qué año cayó el Imperio Romano de Occidente?",
                            opciones = listOf("476 d.C.", "1492", "1453", "1810"),
                            indiceCorrecto = 0
                        ),
                        Pregunta(
                            id = 2,
                            texto = "¿Quién fue el primer emperador romano?",
                            opciones = listOf("Nerón", "Augusto", "Julio César", "Trajano"),
                            indiceCorrecto = 1
                        )
                    )
                ),
                Quiz(
                    id = 2,
                    titulo = "Ciencia y Tecnología",
                    preguntas = listOf(
                        Pregunta(
                            id = 1,
                            texto = "¿Quién desarrolló la teoría de la relatividad?",
                            opciones = listOf("Isaac Newton", "Albert Einstein", "Nikola Tesla", "Stephen Hawking"),
                            indiceCorrecto = 1
                        ),
                        Pregunta(
                            id = 2,
                            texto = "¿Qué unidad mide la intensidad de corriente eléctrica?",
                            opciones = listOf("Voltio", "Amperio", "Ohmio", "Watt"),
                            indiceCorrecto = 1
                        ),
                        Pregunta(
                            id = 3,
                            texto = "¿Qué planeta es conocido como el planeta rojo?",
                            opciones = listOf("Marte", "Venus", "Júpiter", "Mercurio"),
                            indiceCorrecto = 0
                        )
                    )
                ),
                Quiz(
                    id = 3,
                    titulo = "Geografía",
                    preguntas = listOf(
                        Pregunta(
                            id = 1,
                            texto = "¿Cuál es el río más largo del mundo?",
                            opciones = listOf("Amazonas", "Nilo", "Yangtsé", "Misisipi"),
                            indiceCorrecto = 0
                        ),
                        Pregunta(
                            id = 2,
                            texto = "¿En qué continente está Madagascar?",
                            opciones = listOf("África", "Asia", "Oceanía", "Europa"),
                            indiceCorrecto = 0
                        )
                    )
                ),
                Quiz(
                    id = 4,
                    titulo = "Cultura General",
                    preguntas = listOf(
                        Pregunta(
                            id = 1,
                            texto = "¿Cuál es el idioma más hablado en el mundo?",
                            opciones = listOf("Inglés", "Mandarín", "Español", "Hindi"),
                            indiceCorrecto = 1
                        ),
                        Pregunta(
                            id = 2,
                            texto = "¿Qué instrumento tiene teclas, cuerdas y martillos?",
                            opciones = listOf("Guitarra", "Piano", "Arpa", "Violín"),
                            indiceCorrecto = 1
                        )
                    )
                ),
                Quiz(
                    id = 5,
                    titulo = "Deportes",
                    preguntas = listOf(
                        Pregunta(
                            id = 1,
                            texto = "¿Cuántos jugadores hay en un equipo de fútbol?",
                            opciones = listOf("9", "10", "11", "12"),
                            indiceCorrecto = 2
                        ),
                        Pregunta(
                            id = 2,
                            texto = "¿En qué deporte destaca Usain Bolt?",
                            opciones = listOf("Natación", "Atletismo", "Ciclismo", "Fútbol"),
                            indiceCorrecto = 1
                        )
                    )
                ),
                Quiz(
                    id = 6,
                    titulo = "Arte y Literatura",
                    preguntas = listOf(
                        Pregunta(
                            id = 1,
                            texto = "¿Quién pintó 'La noche estrellada'?",
                            opciones = listOf("Picasso", "Van Gogh", "Monet", "Dalí"),
                            indiceCorrecto = 1
                        ),
                        Pregunta(
                            id = 2,
                            texto = "¿Quién escribió 'Don Quijote de la Mancha'?",
                            opciones = listOf("Cervantes", "Lope de Vega", "Góngora", "Garcilaso de la Vega"),
                            indiceCorrecto = 0
                        ),
                        Pregunta(
                            id = 3,
                            texto = "¿Qué corriente artística pertenece a Salvador Dalí?",
                            opciones = listOf("Cubismo", "Surrealismo", "Impresionismo", "Futurismo"),
                            indiceCorrecto = 1
                        )
                    )
                )
            )
        )
    }

    fun registrarRespuesta(quizId: Int, preguntaId: Int, respuestaIndex: Int) {
        val quizIndex = quizzes.indexOfFirst { it.id == quizId }
        if (quizIndex == -1) return

        val quiz = quizzes[quizIndex]
        val preguntasActualizadas = quiz.preguntas.map { pregunta ->
            if (pregunta.id == preguntaId) {
                pregunta.copy(respuestaUsuario = respuestaIndex)
            } else pregunta
        }

        quizzes[quizIndex] = quiz.copy(preguntas = preguntasActualizadas)
    }

    fun calcularPuntaje(quiz: Quiz): Int {
        return quiz.preguntas.count { it.respuestaUsuario == it.indiceCorrecto }
    }
}
