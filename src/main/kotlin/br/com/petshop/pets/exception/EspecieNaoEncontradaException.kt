package br.com.petshop.pets.exception

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class EspecieNaoEncontradaException(mensagem: String): EntityNotFoundException(mensagem) {

}