package br.com.petshop.pets.controller.request

/**
 * Requisição para representar uma raça pelo seu identificador.
 *
 * @property id O identificador único da raça.
 */
data class RacaRequest(
    val id: Int
) {
}