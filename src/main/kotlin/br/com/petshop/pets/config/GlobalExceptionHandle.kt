package br.com.petshop.pets.config

import br.com.petshop.pets.controller.response.ErroResponse
import br.com.petshop.pets.exception.EspecieCamposInvalidosException
import br.com.petshop.pets.exception.EspecieJaRegistradaException
import br.com.petshop.pets.exception.EspecieNaoEncontradaException
import br.com.petshop.pets.exception.RacaJaRegistradaException
import br.com.petshop.pets.exception.RacaNaoEncontradaException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

/**
 * Classe responsável por tratar exceções globalmente na aplicação.
 */
@ControllerAdvice
class GlobalExceptionHandle {

    /**
     * Trata exceções de recursos não encontrados (Espécie e Raça).
     *
     * @param ex A exceção capturada
     * @return ResponseEntity contendo informações do erro
     */
    @ExceptionHandler(
        value = [
            EspecieNaoEncontradaException::class,
            RacaNaoEncontradaException::class
        ]
    )
    fun handleEspecieNaoEncontrada(ex: EspecieNaoEncontradaException): ResponseEntity<ErroResponse> {
        val erro = ErroResponse(404, "Objeto não encontrado:", ex.localizedMessage, LocalDateTime.now())
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro)
    }

    /**
     * Trata exceções de recursos já registrados (Espécie e Raça).
     *
     * @param ex A exceção capturada
     * @return ResponseEntity contendo informações do erro
     */
    @ExceptionHandler(
        value = [
            EspecieJaRegistradaException::class,
            RacaJaRegistradaException::class
        ]
    )
    fun handleEspecieJaRegistrada(ex: EspecieJaRegistradaException): ResponseEntity<ErroResponse> {
        val erro = ErroResponse(400, "Objeto já registrado:", ex.localizedMessage, LocalDateTime.now())
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro)
    }

    /**
     * Trata exceções de campos inválidos em Espécie.
     *
     * @param ex A exceção capturada
     * @return ResponseEntity contendo informações do erro
     */
    @ExceptionHandler(EspecieCamposInvalidosException::class)
    fun handleEspecieCampoInvalido(ex: EspecieCamposInvalidosException): ResponseEntity<ErroResponse> {
        val error = ErroResponse(400, "Campo inválido:", ex.localizedMessage, LocalDateTime.now())
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error)
    }

    /**
     * Trata exceções genéricas não mapeadas.
     *
     * @param ex A exceção capturada
     * @return ResponseEntity contendo informações do erro
     */
    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErroResponse> {
        val erro = ErroResponse(500, "Erro interno:", ex.localizedMessage, LocalDateTime.now())
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro)
    }
}