package br.com.petshop.pets.dto

data class RacaDTO(
    val id: Int,
    val nome: String,
    val especie: EspecieDTO,
    val dataCriacao: String,
    val dataModificacao: String? = null,
)
