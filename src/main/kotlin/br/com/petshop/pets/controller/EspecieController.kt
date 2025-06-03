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
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/especies")
class EspecieController(
    private val service: EspecieService
) {

    @GetMapping(params = ["pagina", "tamanho"])
    fun listarComPaginacao(
        @RequestParam(defaultValue = "0", required = false) pagina: Int,
        @RequestParam(defaultValue = "50", required = false) tamanho: Int
    ): ResponseEntity<PaginacaoResponse<EspecieResponse>> {
        return ResponseEntity.ok(service.listarEspeciesComPaginacao(pagina, tamanho))
    }
    @GetMapping
    fun listarTodosSemPaginacao(): ResponseEntity<List<EspecieResponse>> {
        return ResponseEntity.ok(service.listarEspecies().map { it.toResponse()})
    }

    @GetMapping("/resumida")
    fun listarTodosSemPaginacaoExibindoResultadoResumido(): ResponseEntity<List<EspecieResumeResponse>> {
        return ResponseEntity.ok(service.listarEspecies().map { it.toResumeResponse()})
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable("id") id: Int): ResponseEntity<EspecieDTO> {
        return ResponseEntity.ok(service.buscarEspeciePorId(id))
    }

    @PostMapping
    fun criarEspecie(@RequestBody @Validated criarEspecieRequest: CriarEspecieRequest): ResponseEntity<EspecieDTO> {
        return ResponseEntity.ok(service.criarEspecie(criarEspecieRequest))
    }

    @PatchMapping("/{id}")
    fun atualizarEspecies(
        @PathVariable("id") id: Int,
        @RequestBody  atualizarEspecieRequest: AtualizarEspecieRequest
    ): ResponseEntity<EspecieResponse> {
        preValidacaoEspecieParaAtualizar(id, atualizarEspecieRequest)
        return ResponseEntity.ok(service.atualizarEspecie(especie = atualizarEspecieRequest).toResponse())
    }

    @DeleteMapping("/{id}")
    fun delete(@Validated @PathVariable("id") id: Int): ResponseEntity<DeleteResponse> {
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