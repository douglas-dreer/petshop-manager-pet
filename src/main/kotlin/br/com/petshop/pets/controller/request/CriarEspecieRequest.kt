package br.com.petshop.pets.controller.request

/**
 * Requisição para criação de uma nova espécie.
 *
 * @property nome O nome da espécie a ser criada.
 * @property icone O ícone opcional que representa a espécie.
 */
data class CriarEspecieRequest(
    val nome: String,
    val icone: String? = null,
)