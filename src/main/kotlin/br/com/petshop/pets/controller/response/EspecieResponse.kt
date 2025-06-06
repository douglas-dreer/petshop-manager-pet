package br.com.petshop.pets.controller.response

import java.time.LocalDateTime

/**
 * Classe de resposta para representação de uma espécie.
 *
 * @property id O identificador único da espécie
 * @property nome O nome da espécie
 * @property dataCriacao A data e hora de criação da espécie, por padrão é o momento atual
 * @property dataModificacao A data e hora da última modificação da espécie
 * @property icone O ícone associado à espécie
 */
data class EspecieResponse(
    val id: Int,
    val nome: String,
    val dataCriacao: LocalDateTime? = LocalDateTime.now(),
    val dataModificacao: LocalDateTime? = null,
    val icone: String?,
)