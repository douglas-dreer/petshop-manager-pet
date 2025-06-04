package br.com.petshop.pets.exception

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Exception lançada quando uma espécie procurada não é encontrada no sistema
 *
 * @property mensagem Mensagem detalhando o erro
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
class EspecieNaoEncontradaException(mensagem: String) : EntityNotFoundException(mensagem) {

}