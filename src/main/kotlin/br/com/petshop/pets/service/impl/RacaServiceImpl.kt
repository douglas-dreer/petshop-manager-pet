package br.com.petshop.pets.service.impl

import br.com.petshop.pets.controller.request.AtualizarRacaRequest
import br.com.petshop.pets.controller.request.CriarRacaRequest
import br.com.petshop.pets.controller.response.PaginacaoResponse
import br.com.petshop.pets.controller.response.RacaResponse
import br.com.petshop.pets.dto.RacaDTO
import br.com.petshop.pets.entity.Raca
import br.com.petshop.pets.exception.EspecieNaoEncontradaException
import br.com.petshop.pets.mapper.toDTO
import br.com.petshop.pets.mapper.toEntity
import br.com.petshop.pets.repository.RacaRepository
import br.com.petshop.pets.service.RacaService
import br.com.petshop.pets.validate.RacaValidate
import org.springframework.stereotype.Service

@Service
class RacaServiceImpl(
    private val repository: RacaRepository,
    private val validate: RacaValidate

): RacaService {
    override fun listarRacasSemPaginacao(): List<RacaDTO> {
        return repository.findAll().map { it.toDTO() }
    }

    override fun listarRacasComPaginacao(
        pagina: Int,
        tamanho: Int
    ): PaginacaoResponse<RacaDTO> {
        TODO("Not yet implemented")
    }

    override fun buscarRacaPorId(id: Int): RacaDTO? {
        return repository.findById(id)
            .orElseThrow { EspecieNaoEncontradaException("Não foi encontrada nenhum registro com o id $id") }
            .toDTO()
    }

    /**
     * Criar uma nova raça
     * @param CriarRacaRequest request
     * @return racaDTO [RacaDTO]
     * @see [RacaRepository.save]
     */
    override fun criarRaca(raca: CriarRacaRequest): RacaDTO {
        val entity: Raca = raca.toEntity()
        validate.validarRacaParaInserir(entity)
        return repository.save(entity).toDTO()
    }

    override fun atualizarRaca(raca: AtualizarRacaRequest): RacaDTO {
        TODO("Not yet implemented")
    }

    override fun deletarRaca(id: Int) {
        TODO("Not yet implemented")
    }

}

