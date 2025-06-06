package br.com.petshop.pets.exception

import org.apache.coyote.BadRequestException

/**
 * Exception lançada quando uma raça que está sendo cadastrada já existe no sistema
 *
 * @property mensagem Mensagem detalhando o erro
 */
class RacaJaRegistradaException(mensagem: String) : BadRequestException(mensagem) {
}