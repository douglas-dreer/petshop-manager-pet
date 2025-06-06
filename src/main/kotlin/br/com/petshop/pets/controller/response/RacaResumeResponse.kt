package br.com.petshop.pets.controller.response

/**
 * Classe de resposta resumida para representação de uma raça.
 *
 * @property nome O nome da raça
 * @property especie A espécie a qual a raça pertence
 */
data class RacaResumeResponse(
    val nome: String,
    val especie: EspecieResumeResponse,
)