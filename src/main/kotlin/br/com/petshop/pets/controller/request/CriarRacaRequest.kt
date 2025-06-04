package br.com.petshop.pets.controller.request

data class CriarRacaRequest(
    val nome: String,
    val especie: EspecieRequest
)
