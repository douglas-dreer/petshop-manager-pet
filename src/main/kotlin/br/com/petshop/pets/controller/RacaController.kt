package br.com.petshop.pets.controller

import br.com.petshop.pets.controller.request.AtualizarRacaRequest
import br.com.petshop.pets.controller.request.CriarRacaRequest
import br.com.petshop.pets.controller.response.DeleteResponse
import br.com.petshop.pets.controller.response.PaginacaoResponse
import br.com.petshop.pets.controller.response.RacaResponse
import br.com.petshop.pets.mapper.toResponse
import br.com.petshop.pets.service.RacaService
import br.com.petshop.pets.validate.RacaValidate
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/racas")
@Tag(name = "Raças", description = "Endpoints para gerenciamento de raças de animais")
class RacaController(
    private val service: RacaService,
    private val racaValidate: RacaValidate
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

    @GetMapping(params = ["pagina", "tamanho"])
    @Operation(
        summary = "Lista todas as raças com paginação",
        description = "Retorna todas as raças cadastradas com paginação"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de raças recuperada com sucesso",
        content = [Content(
            mediaType = "application/json",
            schema = Schema(implementation = PaginacaoResponse::class),
            array = io.swagger.v3.oas.annotations.media.ArraySchema(schema = Schema(implementation = RacaResponse::class))
        )]
    )
    @ApiResponse(
        responseCode = "400",
        description = "Parâmetros de paginação inválidos",
        content = [Content(schema = Schema(implementation = Unit::class))]
    )
    fun listarTodosComPaginacao(
        @Parameter(description = "Número da página (iniciando em 0)") @RequestParam(defaultValue = "0") pagina: Int,
        @Parameter(description = "Quantidade de itens por página") @RequestParam(defaultValue = "50") tamanho: Int
    ): ResponseEntity<PaginacaoResponse<RacaResponse>> {
        return ResponseEntity.ok(service.listarRacasComPaginacao(pagina, tamanho))
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

    /**
     * Atualiza uma raça existente
     *
     * @param id ID da raça a ser atualizada
     * @param atualizarRacaRequest Dados da raça a serem atualizados
     * @return A raça atualizada
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza uma raça", description = "Endpoint para atualizar dados de uma raça existente")
    @ApiResponse(
        responseCode = "200",
        description = "Raça atualizada com sucesso",
        content = [Content(schema = Schema(implementation = RacaResponse::class))]
    )
    @ApiResponse(
        responseCode = "400",
        description = "Dados inválidos para atualização",
        content = [Content(schema = Schema(implementation = Unit::class))]
    )
    @ApiResponse(
        responseCode = "404",
        description = "Raça não encontrada",
        content = [Content(schema = Schema(implementation = Unit::class))]
    )
    fun atualizarRaca(
        @Parameter(description = "ID da raça") @PathVariable("id") id: Int,
        @Parameter(description = "Dados para atualização da raça") @RequestBody atualizarRacaRequest: AtualizarRacaRequest
    ): ResponseEntity<RacaResponse> {
        racaValidate.preValidarRacaPraAlteracao(id, atualizarRacaRequest)
        return ResponseEntity.ok(service.atualizarRaca(atualizarRacaRequest).toResponse())
    }

    /**
     * Remove uma raça existente
     *
     * @param id ID da raça a ser removida
     * @return Resposta de confirmação da exclusão
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma raça", description = "Endpoint para remover uma raça existente")
    @ApiResponse(
        responseCode = "200",
        description = "Raça removida com sucesso",
        content = [Content(schema = Schema(implementation = DeleteResponse::class))]
    )
    @ApiResponse(
        responseCode = "404",
        description = "Raça não encontrada",
        content = [Content(schema = Schema(implementation = Unit::class))]
    )
    fun delete(
        @Parameter(description = "ID da raça") @PathVariable("id") id: Int,
    ): ResponseEntity<DeleteResponse> {
        service.deletarRaca(id)
        return ResponseEntity.ok(
            DeleteResponse(200, "Exclusão de raça", "A raça foi excluída com sucesso", LocalDateTime.now())
        )
    }
}