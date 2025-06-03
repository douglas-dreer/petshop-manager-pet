package br.com.petshop.pets.factory

import br.com.petshop.pets.controller.request.AtualizarEspecieRequest
import br.com.petshop.pets.controller.request.CriarEspecieRequest
import br.com.petshop.pets.dto.EspecieDTO
import br.com.petshop.pets.entity.Especie

class EspecieFactory {
    companion object {
        fun criarEntity(): Especie {
            return Especie(1, "Felino", "felino.png")
        }

        fun criarDTO(): EspecieDTO {
            return EspecieDTO(1, "Felino", "felino.png")
        }

        fun criarObjetoParaInserir(): CriarEspecieRequest {
            return CriarEspecieRequest(nome = "Felino", icone = "felino.png")
        }

        fun criarObjetoParaAtualizar(): AtualizarEspecieRequest {
            return AtualizarEspecieRequest(id = 1, nome = "Felino", icone = "felino.png")
        }
    }
}

