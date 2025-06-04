package br.com.petshop.pets.controller.response

import java.time.LocalDateTime

/**
 * Classe de resposta para operações de exclusão.
 *
 * @property codigo O código de status da resposta
 * @property titulo O título da resposta
 * @property mensagem A mensagem detalhada da resposta
 * @property dataHora A data e hora da resposta, por padrão é o momento atual
 */
data class DeleteResponse(
    val codigo: Int,
    val titulo: String,
    val mensagem: String,
    val dataHora: LocalDateTime = LocalDateTime.now()
)