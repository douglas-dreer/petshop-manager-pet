package br.com.petshop.pets.assembler

import br.com.petshop.pets.controller.request.AtualizarRacaRequest
import br.com.petshop.pets.controller.request.CriarRacaRequest

import br.com.petshop.pets.dto.EspecieDTO
import br.com.petshop.pets.entity.Especie
import br.com.petshop.pets.entity.Raca
import br.com.petshop.pets.mapper.toEntity
import br.com.petshop.pets.service.EspecieService
import org.springframework.stereotype.Component

/**
 * A classe `RacaAssembler` é responsável por converter objetos de transferência de dados (DTOs) ou objetos de requisição
 * em entidades `Raca`. Ela atua como um montador para abstrair e simplificar a lógica de conversão,
 * aproveitando serviços como `EspecieService` para recuperar dados necessários para preencher propriedades associadas.
 *
 * @param especieService O serviço responsável por recuperar dados de `Especie` a serem utilizados no processo de montagem.
 */
@Component
class RacaAssembler(
    private val especieService: EspecieService
) {
    val especieDefault = Especie(id = 0, nome = "Desconhecida", icone = "Desconhecido")

    /**
     * Converte um objeto `CriarRacaRequest` em uma entidade `Raca`.
     *
     * @param criarRaca O objeto de requisição contendo os dados a serem convertidos em uma entidade `Raca`.
     * @return Uma instância de entidade `Raca` construída a partir dos dados da requisição fornecida.
     */
    fun toEntity(criarRaca: CriarRacaRequest): Raca {
        val especieId = criarRaca.especie.id
        val especie: Especie? = obtemDadosEspecie(especieId)?.toEntity()

        return Raca(
            id = 0,
            nome = criarRaca.nome,
            dataCriacao = java.time.LocalDateTime.now(),
            dataModificacao = null,
            especie = especie ?: this.especieDefault
        )
    }

    /**
     * Converte um objeto `AtualizarRacaRequest` em uma entidade `Raca`.
     *
     * @param atualizarRaca O objeto de requisição contendo os dados a serem convertidos em uma entidade `Raca`.
     * @return Uma entidade `Raca` construída a partir dos dados da requisição fornecida.
     */
    fun toEntity(atualizarRaca: AtualizarRacaRequest): Raca {
        val especieId = atualizarRaca.especie.id
        val especie: Especie? = obtemDadosEspecie(especieId)?.toEntity()

        return Raca(
            id = 0,
            nome = atualizarRaca.nome,
            dataCriacao = java.time.LocalDateTime.now(),
            dataModificacao = null,
            especie = especie ?: this.especieDefault
        )
    }

    private fun obtemDadosEspecie(especieId: Int): EspecieDTO? {
        return especieService.buscarEspeciePorId(especieId)
    }
}