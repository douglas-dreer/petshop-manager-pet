package br.com.petshop.pets.mapper


import br.com.petshop.pets.controller.request.CriarRacaRequest
import br.com.petshop.pets.controller.response.RacaResponse
import br.com.petshop.pets.dto.RacaDTO
import br.com.petshop.pets.entity.Raca
import java.time.LocalDateTime


/**
 * Converte um objeto [CriarRacaRequest] em uma entidade [Raca].
 *
 * @return Uma nova instância de [Raca] contendo:
 * - [Raca.id] iniciado com valor 0
 * - [Raca.nome] copiado do request
 * - [Raca.especie] convertido do request para entidade
 * - [Raca.dataCriacao] com valor atual
 * - [Raca.dataModificacao] iniciado como nulo
 */
fun CriarRacaRequest.toEntity(): Raca = Raca(
    id = 0,
    nome = this.nome,
    especie = this.especie.toEntity(),
    dataCriacao = LocalDateTime.now(),
    dataModificacao = null
)

/**
 * Converte uma entidade [Raca] em um objeto [RacaDTO].
 *
 * @return Uma nova instância de [RacaDTO] contendo:
 * - [RacaDTO.id] copiado da entidade
 * - [RacaDTO.nome] copiado da entidade
 * - [RacaDTO.especie] convertido da entidade para DTO
 * - [RacaDTO.dataCriacao] copiado da entidade
 * - [RacaDTO.dataModificacao] copiado da entidade
 */
fun Raca.toDTO(): RacaDTO = RacaDTO(
    id = this.id,
    nome = this.nome,
    especie = this.especie.toDTO(),
    dataCriacao = this.dataCriacao,
    dataModificacao = this.dataModificacao
)

/**
 * Converte uma entidade [Raca] em um objeto [RacaResponse].
 *
 * @return Uma nova instância de [RacaResponse] contendo:
 * - [RacaResponse.id] copiado da entidade
 * - [RacaResponse.nome] copiado da entidade
 * - [RacaResponse.especie] convertido da entidade para response
 * - [RacaResponse.dataCriacao] copiado da entidade
 * - [RacaResponse.dataModificacao] copiado da entidade
 */
fun Raca.toResponse(): RacaResponse = RacaResponse(
    id = this.id,
    nome = this.nome,
    especie = this.especie.toResponse(),
    dataCriacao = this.dataCriacao,
    dataModificacao = this.dataModificacao
)

/**
 * Converte um [RacaDTO] em um objeto [RacaResponse].
 *
 * @return Uma nova instância de [RacaResponse] contendo:
 * - [RacaResponse.id] copiado do DTO
 * - [RacaResponse.nome] copiado do DTO
 * - [RacaResponse.especie] convertido do DTO para response
 * - [RacaResponse.dataCriacao] copiado do DTO
 * - [RacaResponse.dataModificacao] copiado do DTO
 */
fun RacaDTO.toResponse(): RacaResponse = RacaResponse(
    id = this.id,
    nome = this.nome,
    especie = this.especie.toResponse(),
    dataCriacao = this.dataCriacao,
    dataModificacao = this.dataModificacao
)

/**
 * Converte um [RacaDTO] em uma entidade [Raca].
 *
 * @return Uma nova instância de [Raca] contendo:
 * - [Raca.id] copiado do DTO
 * - [Raca.nome] copiado do DTO
 * - [Raca.especie] convertido do DTO para entidade
 * - [Raca.dataCriacao] copiado do DTO
 * - [Raca.dataModificacao] copiado do DTO
 */
fun RacaDTO.toEntity(): Raca = Raca(
    id = this.id,
    nome = this.nome,
    especie = this.especie.toEntity(),
    dataCriacao = this.dataCriacao,
    dataModificacao = this.dataModificacao
)