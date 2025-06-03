package br.com.petshop.pets.controller.response

import java.time.LocalDateTime

data class ErroResponse(
    val codigo: Int,
    val titulo: String,
    val mensagem: String,
    val dataHora: LocalDateTime = LocalDateTime.now()
)
