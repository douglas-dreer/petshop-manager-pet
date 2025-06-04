package br.com.petshop.pets.mapper

import br.com.petshop.pets.controller.request.AtualizarEspecieRequest
import br.com.petshop.pets.controller.request.CriarEspecieRequest
import br.com.petshop.pets.controller.request.EspecieRequest
import br.com.petshop.pets.controller.response.EspecieResponse
import br.com.petshop.pets.controller.response.EspecieResumeResponse
import br.com.petshop.pets.dto.EspecieDTO
import br.com.petshop.pets.entity.Especie
import java.time.LocalDateTime

/**
 * Converte um [CriarEspecieRequest] para [Especie]
 *
 * @return Entidade [Especie] com os dados do request
 */
fun CriarEspecieRequest.toEntity(): Especie = Especie(
    id = 0,
    nome = this.nome,
    icone = this.icone,
    racas = emptyList(),
    dataCriacao = LocalDateTime.now(),
    dataModificacao = null
)

/**
 * Converte um [AtualizarEspecieRequest] para [Especie]
 *
 * @return Entidade [Especie] com os dados do request de atualização
 */
fun AtualizarEspecieRequest.toEntity(): Especie = Especie(
    id = this.id,
    nome = this.nome
)

/**
 * Converte uma [Especie] para [EspecieDTO]
 *
 * @return DTO com os dados da espécie
 */
fun Especie.toDTO(): EspecieDTO = EspecieDTO(
    id = this.id,
    nome = this.nome,
    icone = this.icone,
    dataCriacao = this.dataCriacao,
    dataModificacao = this.dataModificacao
)

/**
 * Converte um [EspecieDTO] para [EspecieResponse]
 *
 * @return Response com os dados da espécie
 */
fun EspecieDTO.toResponse(): EspecieResponse = EspecieResponse(
    id = this.id,
    nome = this.nome,
    icone = this.icone,
    dataCriacao = this.dataCriacao,
    dataModificacao = this.dataModificacao
)

/**
 * Converte uma [Especie] para [EspecieResponse]
 *
 * @return Response com os dados da espécie
 */
fun Especie.toResponse(): EspecieResponse = EspecieResponse(
    id = this.id,
    nome = this.nome,
    icone = this.icone,
    dataCriacao = this.dataCriacao,
    dataModificacao = this.dataModificacao,
)

/**
 * Converte um [EspecieDTO] para [EspecieResumeResponse]
 *
 * @return Response resumido com os dados da espécie
 */
fun EspecieDTO.toResumeResponse(): EspecieResumeResponse = EspecieResumeResponse(
    nome = this.nome,
    icone = this.icone,
)

/**
 * Converte um [EspecieDTO] para [Especie]
 *
 * @return Entidade [Especie] com os dados do DTO
 */
fun EspecieDTO.toEntity(): Especie = Especie(
    id = this.id,
    nome = this.nome,
    icone = this.icone,
    dataCriacao = this.dataCriacao,
    dataModificacao = this.dataModificacao,
)

/**
 * Converte um [EspecieResponse] para [Especie]
 *
 * @return Entidade [Especie] com os dados do response
 */
fun EspecieResponse.toEntity(): Especie = Especie(
    id = this.id,
    nome = this.nome,
    icone = this.icone,
    dataCriacao = this.dataCriacao,
    dataModificacao = this.dataModificacao,
)

/**
 * Converte um [EspecieResumeResponse] para [Especie]
 *
 * @return Entidade [Especie] com os dados do response resumido
 */
fun EspecieResumeResponse.toEntity(): Especie = Especie(
    id = 0,
    nome = this.nome,
    icone = this.icone,
    dataCriacao = LocalDateTime.now(),
    dataModificacao = null
)

/**
 * Converte um [EspecieRequest] para [Especie]
 *
 * @return Entidade [Especie] com os dados do request
 */
fun EspecieRequest.toEntity(): Especie = Especie(
    id = this.id,
    nome = "",
    icone = null,
    dataCriacao = null,
    dataModificacao = null
)