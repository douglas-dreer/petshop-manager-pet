package br.com.petshop.pets.factory

import br.com.petshop.pets.controller.request.AtualizarEspecieRequest
import br.com.petshop.pets.controller.request.CriarEspecieRequest
import br.com.petshop.pets.dto.EspecieDTO
import br.com.petshop.pets.entity.Especie

/**
 * Fábrica responsável por criar objetos relacionados à entidade Especie para testes.
 */
class EspecieFactory {
    companion object {
        /**
         * Cria uma entidade Especie com valores predefinidos.
         *
         * @return Uma instância de Especie para uso em testes
         */
        fun criarEntity(): Especie {
            return Especie(1, "Felino", "felino.png")
        }

        /**
         * Cria um DTO de Especie com valores predefinidos.
         *
         * @return Uma instância de EspecieDTO para uso em testes
         */
        fun criarDTO(): EspecieDTO {
            return EspecieDTO(1, "Felino", "felino.png")
        }

        /**
         * Cria um objeto de requisição para inserção de espécie.
         *
         * @return Uma instância de CriarEspecieRequest para uso em testes
         */
        fun criarObjetoParaInserir(): CriarEspecieRequest {
            return CriarEspecieRequest(nome = "Felino", icone = "felino.png")
        }

        /**
         * Cria um objeto de requisição para atualização de espécie.
         *
         * @return Uma instância de AtualizarEspecieRequest para uso em testes
         */
        fun criarObjetoParaAtualizar(): AtualizarEspecieRequest {
            return AtualizarEspecieRequest(id = 1, nome = "Felino", icone = "felino.png")
        }

        /**
         * Cria uma instância de EspecieDTO com o ID fornecido e valores predefinidos para os demais campos.
         *
         * @param id O identificador único da espécie a ser atribuída ao DTO.
         * @return Uma instância de EspecieDTO contendo o ID fornecido, além de um nome e ícone predefinidos.
         */
        fun criarDTOComId(id: Int): EspecieDTO {
            return EspecieDTO(id, "Felino", "felino.png")
        }
    }
}