package br.com.petshop.pets.mapper

import br.com.petshop.pets.controller.request.CriarRacaRequest
import br.com.petshop.pets.controller.response.RacaResponse
import br.com.petshop.pets.dto.RacaDTO
import br.com.petshop.pets.entity.Raca
import java.time.LocalDateTime

fun CriarRacaRequest.toEntity(): Raca = Raca(
    id = 0,
    nome = this.nome,
    especie = this.especie.toEntity(),
    dataCriacao = LocalDateTime.now(),
    dataModificacao = null
)

fun Raca.toDTO(): RacaDTO = RacaDTO(
    id = this.id,
    nome = this.nome,
    especie = this.especie.toDTO(),
    dataCriacao = this.dataCriacao.toString(),
    dataModificacao = this.dataModificacao?.toString()
)

fun RacaDTO.toResponse(): RacaResponse = RacaResponse(
    id = this.id,
    nome = this.nome,
    especie = this.especie.toResponse(),
    dataCriacao = this.dataCriacao,
    dataModificacao = this.dataModificacao
)

