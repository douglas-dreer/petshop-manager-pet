package br.com.petshop.pets.controller.request

data class CriarEspecieRequest(
    val nome: String,
    val icone: String? = null,
)
