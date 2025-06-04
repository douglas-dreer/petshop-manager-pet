package br.com.petshop.pets.controller.response

import java.time.LocalDateTime

data class RacaResponse(
    val id: Int,
    val nome: String,
    val dataCriacao: LocalDateTime,
    val dataModificacao: LocalDateTime? = null,
    val especie: EspecieResponse,
)
