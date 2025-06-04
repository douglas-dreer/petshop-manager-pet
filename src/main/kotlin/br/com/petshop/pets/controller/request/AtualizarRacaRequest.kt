package br.com.petshop.pets.controller.request

import br.com.petshop.pets.entity.Especie
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

/**
 * Requisição para atualização de uma raça existente.
 *
 * @property id O identificador único da raça a ser atualizada. Não pode ser nulo.
 * @property nome O novo nome da raça.
 * @property especie A espécie à qual a raça pertence. Não pode ser nula nem vazia.
 */
data class AtualizarRacaRequest(
    @NotNull val id: Int,
    val nome: String,
    @NotNull @NotBlank
    val especie: Especie
)