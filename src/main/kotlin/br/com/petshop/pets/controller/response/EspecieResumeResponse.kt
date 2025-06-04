package br.com.petshop.pets.controller.response

/**
 * Classe de resposta resumida para representação de uma espécie.
 *
 * @property nome O nome da espécie
 * @property icone O ícone associado à espécie
 */
data class EspecieResumeResponse(
    val nome: String,
    val icone: String?,
)