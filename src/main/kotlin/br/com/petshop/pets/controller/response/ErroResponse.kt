package br.com.petshop.pets.controller.response

import java.time.LocalDateTime

/**
 * Classe de resposta para situações de erro.
 *
 * @property codigo O código de erro
 * @property titulo O título do erro
 * @property mensagem A mensagem detalhada do erro
 * @property dataHora A data e hora em que o erro ocorreu, por padrão é o momento atual
 */
data class ErroResponse(
    val codigo: Int,
    val titulo: String,
    val mensagem: String,
    val dataHora: LocalDateTime = LocalDateTime.now()
)