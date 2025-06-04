package br.com.petshop.pets.dto

import java.time.LocalDateTime

/**
 * Objeto de transferência de dados (DTO) que representa uma espécie.
 *
 * @property id O identificador único da espécie.
 * @property nome O nome da espécie.
 * @property icone O ícone opcional que representa a espécie.
 * @property dataCriacao A data e hora em que a espécie foi criada (por padrão, é o momento atual).
 * @property dataModificacao A data e hora da última modificação da espécie, ou null se nunca foi modificada.
 */
data class EspecieDTO(
    val id: Int,
    val nome: String,
    val icone: String?,
    val dataCriacao: LocalDateTime? = LocalDateTime.now(),
    val dataModificacao: LocalDateTime? = null,
)