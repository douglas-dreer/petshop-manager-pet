package br.com.petshop.pets.dto

import java.time.LocalDateTime

data class RacaDTO(
    val id: Int,
    val nome: String,
    val especie: EspecieDTO,
    val dataCriacao: LocalDateTime,
    val dataModificacao: LocalDateTime? = null,
)
