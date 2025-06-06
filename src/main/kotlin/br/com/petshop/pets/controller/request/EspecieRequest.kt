package br.com.petshop.pets.controller.request

/**
 * Requisição para representar uma espécie pelo seu identificador.
 *
 * @property id O identificador único da espécie.
 */
data class EspecieRequest(
    val id: Int
) {
}