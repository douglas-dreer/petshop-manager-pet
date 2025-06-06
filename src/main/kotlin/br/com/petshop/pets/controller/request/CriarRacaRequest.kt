package br.com.petshop.pets.controller.request

/**
 * Requisição para criação de uma nova raça.
 *
 * @property nome O nome da raça a ser criada
 * @property especie A espécie à qual a raça pertence
 */
data class CriarRacaRequest(
    val nome: String,
    val especie: EspecieRequest
)