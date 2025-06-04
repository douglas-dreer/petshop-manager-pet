package br.com.petshop.pets.service

import br.com.petshop.pets.controller.request.AtualizarRacaRequest
import br.com.petshop.pets.controller.request.CriarRacaRequest
import br.com.petshop.pets.controller.response.PaginacaoResponse
import br.com.petshop.pets.dto.RacaDTO

interface RacaService {
    fun listarRacasSemPaginacao(): List<RacaDTO>
    fun listarRacasComPaginacao(pagina: Int, tamanho: Int): PaginacaoResponse<RacaDTO>
    fun buscarRacaPorId(id: Int): RacaDTO?

    fun criarRaca(raca: CriarRacaRequest): RacaDTO
    fun atualizarRaca(raca: AtualizarRacaRequest): RacaDTO
    fun deletarRaca(id: Int)
}