package br.com.petshop.pets.controller

import br.com.petshop.pets.controller.request.CriarRacaRequest
import br.com.petshop.pets.controller.response.RacaResponse
import br.com.petshop.pets.mapper.toResponse
import br.com.petshop.pets.service.RacaService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/racas")
class RacaController(
    private val service: RacaService
) {
    @PostMapping
    fun criarRaca(@RequestBody @Validated request: CriarRacaRequest): ResponseEntity<RacaResponse> {
        return ResponseEntity.ok(service.criarRaca(request).toResponse())
    }

    @GetMapping
    fun listarTodosSemPaginacao(): ResponseEntity<List<RacaResponse>> {
        return ResponseEntity.ok(service.listarRacasSemPaginacao().map { it.toResponse()})
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable("id") id: Int): ResponseEntity<RacaResponse> {
        return ResponseEntity.ok(service.buscarRacaPorId(id)?.toResponse())
    }
}