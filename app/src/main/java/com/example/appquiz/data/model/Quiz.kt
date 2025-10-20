package com.example.appquiz.data.model

// Una pregunta individual
data class Quiz(
    val id: Int,
    val titulo: String,
    val preguntas: List<Pregunta>
)

data class Pregunta(
    val id: Int,
    val texto: String,
    val opciones: List<String>,
    val indiceCorrecto: Int,
    var respuestaUsuario: Int? = null
)


