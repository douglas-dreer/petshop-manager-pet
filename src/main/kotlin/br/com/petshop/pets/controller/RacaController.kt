package br.com.petshop.pets.controller

import br.com.petshop.pets.controller.request.CriarRacaRequest
import br.com.petshop.pets.controller.response.RacaResponse
import br.com.petshop.pets.mapper.toResponse
import br.com.petshop.pets.service.RacaService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
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
@Tag(name = "Raças", description = "Endpoints para gerenciamento de raças de animais")
class RacaController(
    private val service: RacaService
) {
    /**
     * Cria uma nova raça
     *
     * @param request Dados da raça a ser criada
     * @return A raça criada
     */
    @PostMapping
    @Operation(summary = "Cria uma nova raça", description = "Endpoint para criar uma nova raça de animal")
    @ApiResponse(
        responseCode = "200",
        description = "Raça criada com sucesso",
        content = [Content(schema = Schema(implementation = RacaResponse::class))]
    )
    fun criarRaca(@RequestBody @Validated request: CriarRacaRequest): ResponseEntity<RacaResponse> {
        return ResponseEntity.ok(service.criarRaca(request).toResponse())
    }

    /**
     * Lista todas as raças cadastradas sem paginação
     *
     * @return Lista de todas as raças
     */
    @GetMapping
    @Operation(summary = "Lista todas as raças", description = "Retorna todas as raças cadastradas sem paginação")
    @ApiResponse(
        responseCode = "200",
        description = "Lista de raças recuperada com sucesso",
        content = [Content(schema = Schema(implementation = RacaResponse::class))]
    )
    fun listarTodosSemPaginacao(): ResponseEntity<List<RacaResponse>> {
        return ResponseEntity.ok(service.listarRacasSemPaginacao().map { it.toResponse() })
    }

    /**
     * Busca uma raça pelo ID
     *
     * @param id ID da raça a ser buscada
     * @return A raça encontrada ou null se não existir
     */
    @GetMapping("/{id}")
    @Operation(summary = "Busca raça por ID", description = "Retorna uma raça específica baseada no ID fornecido")
    @ApiResponse(
        responseCode = "200",
        description = "Raça encontrada com sucesso",
        content = [Content(schema = Schema(implementation = RacaResponse::class))]
    )
    @ApiResponse(
        responseCode = "404",
        description = "Raça não encontrada",
        content = [Content(schema = Schema(implementation = Unit::class))]
    )
    fun buscarPorId(@PathVariable("id") id: Int): ResponseEntity<RacaResponse> {
        return ResponseEntity.ok(service.buscarRacaPorId(id)?.toResponse())
    }
}