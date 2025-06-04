package br.com.petshop.pets.exception

import org.apache.coyote.BadRequestException


class RacaCamposInvalidosException(mensagem: String): BadRequestException(mensagem) {
}