package br.com.petshop.pets.dto

import java.time.LocalDateTime

data class EspecieDTO(
    val id: Int,
    val nome: String,
    val icone: String?,
    val dataCriacao: LocalDateTime? = LocalDateTime.now(),
    val dataModificacao: LocalDateTime? = null,
)