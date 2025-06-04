package br.com.petshop.pets.controller

import br.com.petshop.pets.controller.request.AtualizarEspecieRequest
import br.com.petshop.pets.controller.request.CriarEspecieRequest
import br.com.petshop.pets.controller.response.DeleteResponse
import br.com.petshop.pets.controller.response.EspecieResponse
import br.com.petshop.pets.controller.response.EspecieResumeResponse
import br.com.petshop.pets.controller.response.PaginacaoResponse
import br.com.petshop.pets.dto.EspecieDTO
import br.com.petshop.pets.exception.EspecieCamposInvalidosException
import br.com.petshop.pets.mapper.toResponse
import br.com.petshop.pets.mapper.toResumeResponse
import br.com.petshop.pets.service.EspecieService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

/**
 * Controlador REST para gerenciamento de espécies de animais.
 * Disponibiliza endpoints para operações CRUD relacionadas às espécies.
 *
 * @property service Serviço que contém a lógica de negócio para operações com espécies
 */
@RestController
@RequestMapping("/especies")
@Tag(name = "Espécies", description = "API para gerenciamento de espécies de animais")
class EspecieController(
    private val service: EspecieService
) {

    @Operation(summary = "Lista espécies com paginação")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Lista de espécies retornada com sucesso"),
        ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = [Content()])
    )
    @GetMapping(params = ["pagina", "tamanho"])
    fun listarComPaginacao(
        @Parameter(description = "Número da página (começa em 0)") @RequestParam(
            defaultValue = "0",
            required = false
        ) pagina: Int,
        @Parameter(description = "Quantidade de registros por página") @RequestParam(
            defaultValue = "50",
            required = false
        ) tamanho: Int
    ): ResponseEntity<PaginacaoResponse<EspecieResponse>> {
        return ResponseEntity.ok(service.listarEspeciesComPaginacao(pagina, tamanho))
    }

    @Operation(summary = "Lista todas as espécies sem paginação")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Lista completa de espécies retornada com sucesso"),
        ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = [Content()])
    )
    @GetMapping
    fun listarTodosSemPaginacao(): ResponseEntity<List<EspecieResponse>> {
        return ResponseEntity.ok(service.listarEspecies().map { it.toResponse() })
    }

    @Operation(summary = "Lista espécies em formato resumido")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Lista resumida de espécies retornada com sucesso"),
        ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = [Content()])
    )
    @GetMapping("/resumida")
    fun listarTodosSemPaginacaoExibindoResultadoResumido(): ResponseEntity<List<EspecieResumeResponse>> {
        return ResponseEntity.ok(service.listarEspecies().map { it.toResumeResponse() })
    }

    @Operation(summary = "Busca espécie por ID")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Espécie encontrada com sucesso"),
        ApiResponse(responseCode = "404", description = "Espécie não encontrada"),
        ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = [Content()])
    )
    @GetMapping("/{id}")
    fun buscarPorId(@Parameter(description = "ID da espécie") @PathVariable("id") id: Int): ResponseEntity<EspecieDTO> {
        return ResponseEntity.ok(service.buscarEspeciePorId(id))
    }

    @Operation(summary = "Cria uma nova espécie")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Espécie criada com sucesso"),
        ApiResponse(responseCode = "400", description = "Dados inválidos"),
        ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = [Content()])
    )
    @PostMapping
    fun criarEspecie(@RequestBody @Validated criarEspecieRequest: CriarEspecieRequest): ResponseEntity<EspecieDTO> {
        return ResponseEntity.ok(service.criarEspecie(criarEspecieRequest))
    }

    @Operation(summary = "Atualiza uma espécie existente")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Espécie atualizada com sucesso"),
        ApiResponse(responseCode = "400", description = "Dados inválidos"),
        ApiResponse(responseCode = "404", description = "Espécie não encontrada"),
        ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = [Content()])
    )
    @PatchMapping("/{id}")
    fun atualizarEspecies(
        @Parameter(description = "ID da espécie") @PathVariable("id") id: Int,
        @RequestBody atualizarEspecieRequest: AtualizarEspecieRequest
    ): ResponseEntity<EspecieResponse> {
        preValidacaoEspecieParaAtualizar(id, atualizarEspecieRequest)
        return ResponseEntity.ok(service.atualizarEspecie(especie = atualizarEspecieRequest).toResponse())
    }

    @Operation(summary = "Remove uma espécie")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Espécie removida com sucesso"),
        ApiResponse(responseCode = "404", description = "Espécie não encontrada"),
        ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = [Content()])
    )
    @DeleteMapping("/{id}")
    fun delete(@Parameter(description = "ID da espécie") @Validated @PathVariable("id") id: Int): ResponseEntity<DeleteResponse> {
        service.deletarEspecie(id)
        return ResponseEntity.ok(
            DeleteResponse(200, "Exclusão de Especíe", "A espécie foi excluída com sucesso", LocalDateTime.now())
        )
    }

    private fun preValidacaoEspecieParaAtualizar(id: Int, especieId: AtualizarEspecieRequest) {
        if (id.equals(especieId.id).not()) {
            throw EspecieCamposInvalidosException("O campo(s) passados estão em branco, nulos ou são inválidos")
        }
    }
}