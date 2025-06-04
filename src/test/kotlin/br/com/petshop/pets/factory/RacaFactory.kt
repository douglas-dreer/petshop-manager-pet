package br.com.petshop.pets.factory

import br.com.petshop.pets.controller.request.AtualizarRacaRequest
import br.com.petshop.pets.controller.request.CriarRacaRequest
import br.com.petshop.pets.controller.request.EspecieRequest
import br.com.petshop.pets.dto.RacaDTO
import br.com.petshop.pets.entity.Especie
import br.com.petshop.pets.entity.Raca
import java.time.LocalDateTime

/**
 * Fábrica responsável por criar objetos relacionados à entidade Raca para testes.
 */
class RacaFactory {

    companion object {
        /**
         * Cria uma entidade Raca com valores predefinidos.
         *
         * @return Uma instância de Raca para uso em testes
         */
        fun criarEntity(): Raca {
            val especie: Especie = EspecieFactory.criarEntity()
            return Raca(
                id = 1,
                nome = "Labrador",
                especie = especie,
                dataCriacao = LocalDateTime.now().minusDays(7),
                dataModificacao = LocalDateTime.now(),
            )
        }

        /**
         * Cria um DTO de Raca com valores predefinidos.
         *
         * @return Uma instância de RacaDTO para uso em testes
         */
        fun criarDTO(): RacaDTO = RacaDTO(
            id = 1,
            nome = "Labrador",
            especie = EspecieFactory.criarDTO(),
            dataCriacao = LocalDateTime.now().minusDays(7),
            dataModificacao = LocalDateTime.now(),
        )

        /**
         * Cria um objeto de requisição para inserção de raça.
         *
         * @return Uma instância de CriarRacaRequest para uso em testes
         */
        fun criarObjetoParaInserir(): CriarRacaRequest {
            return CriarRacaRequest(nome = "Felino", EspecieRequest(1))
        }

        /**
         * Cria um objeto de requisição para atualização de raça.
         *
         * @return Uma instância de AtualizarRacaRequest para uso em testes
         */
        fun criarObjetoParaAtualizar(): AtualizarRacaRequest {
            return AtualizarRacaRequest(
                id = 1,
                nome = "Felino",
                especie = EspecieRequest(1)
            )
        }
    }
}