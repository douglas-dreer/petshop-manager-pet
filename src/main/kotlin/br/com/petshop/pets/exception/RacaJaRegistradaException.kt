package br.com.petshop.pets.exception

import org.apache.coyote.BadRequestException

class RacaJaRegistradaException(mensagem: String): BadRequestException(mensagem) {
}