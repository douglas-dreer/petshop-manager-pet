package br.com.petshop.pets.exception

import org.apache.coyote.BadRequestException

class EspecieJaRegistradaException(mensagem: String): BadRequestException(mensagem) {
}