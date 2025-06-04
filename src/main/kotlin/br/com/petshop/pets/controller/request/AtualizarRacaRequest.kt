package br.com.petshop.pets.controller.request

import br.com.petshop.pets.entity.Especie
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AtualizarRacaRequest(
    @NotNull val id: Int,
    val nome: String,
    @NotNull @NotBlank
    val especie: Especie
)
