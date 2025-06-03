package br.com.petshop.pets.service

import br.com.petshop.pets.controller.request.AtualizarEspecieRequest
import br.com.petshop.pets.controller.request.CriarEspecieRequest
import br.com.petshop.pets.controller.response.EspecieResponse
import br.com.petshop.pets.controller.response.PaginacaoResponse
import br.com.petshop.pets.dto.EspecieDTO

interface EspecieService {
    fun listarEspecies(): List<EspecieDTO>
    fun listarEspeciesComPaginacao(pagina: Int, tamanho: Int): PaginacaoResponse<EspecieResponse>
    fun buscarEspeciePorId(id: Int): EspecieDTO?
    fun criarEspecie(especie: CriarEspecieRequest): EspecieDTO
    fun atualizarEspecie(especie: AtualizarEspecieRequest): EspecieDTO
    fun deletarEspecie(id: Int)
}