package br.com.petshop.pets.controller.response

import java.time.LocalDateTime

data class EspecieResponse(
    val id: Int,
    val nome: String,
    val dataCriacao: LocalDateTime? = LocalDateTime.now(),
    val dataModificacao: LocalDateTime? = null,
    val icone: String?,
)
