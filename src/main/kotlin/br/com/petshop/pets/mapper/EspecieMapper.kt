package br.com.petshop.pets.mapper

import br.com.petshop.pets.controller.request.AtualizarEspecieRequest
import br.com.petshop.pets.controller.request.CriarEspecieRequest
import br.com.petshop.pets.controller.response.EspecieResponse
import br.com.petshop.pets.controller.response.EspecieResumeResponse
import br.com.petshop.pets.dto.EspecieDTO
import br.com.petshop.pets.entity.Especie
import java.time.LocalDateTime

fun CriarEspecieRequest.toEntity(): Especie = Especie(
    id = 0,
    nome = this.nome,
    icone = this.icone,
    racas = emptyList(),
    dataCriacao = LocalDateTime.now(),
    dataModificacao = null
)

fun AtualizarEspecieRequest.toEntity(): Especie = Especie(
    id = this.id,
    nome = this.nome
)

fun Especie.toDTO(): EspecieDTO = EspecieDTO(
    id = this.id,
    nome = this.nome,
    icone = this.icone,
    dataCriacao =  this.dataCriacao,
    dataModificacao = this.dataModificacao
)

fun EspecieDTO.toResponse(): EspecieResponse = EspecieResponse(
    id = this.id,
    nome = this.nome,
    icone = this.icone,
    dataCriacao = this.dataCriacao,
    dataModificacao = this.dataModificacao
)

fun Especie.toResponse(): EspecieResponse = EspecieResponse(
    id = this.id,
    nome = this.nome,
    icone = this.icone,
    dataCriacao =  this.dataCriacao,
    dataModificacao = this.dataModificacao,
)

fun EspecieDTO.toResumeResponse(): EspecieResumeResponse = EspecieResumeResponse(
    nome = this.nome,
    icone = this.icone,

)
