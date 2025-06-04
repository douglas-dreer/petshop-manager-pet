package br.com.petshop.pets.controller.response

data class RacaResponse(
    val id: Int,
    val nome: String,
    val dataCriacao: String,
    val dataModificacao: String? = null,
    val especie: EspecieResponse,
)
