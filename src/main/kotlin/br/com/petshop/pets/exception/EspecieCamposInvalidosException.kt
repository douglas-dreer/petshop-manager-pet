package br.com.petshop.pets.exception

import org.apache.coyote.BadRequestException

class EspecieCamposInvalidosException(mensagem: String): BadRequestException(mensagem) {
}