package br.com.petshop.pets.controller.request

data class AtualizarEspecieRequest(
    val id: Int,
    val nome: String,
    val icone: String? = null,
)
