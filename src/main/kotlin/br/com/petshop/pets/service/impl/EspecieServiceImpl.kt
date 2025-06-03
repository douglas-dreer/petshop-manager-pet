package br.com.petshop.pets.service.impl

import br.com.petshop.pets.controller.request.AtualizarEspecieRequest
import br.com.petshop.pets.controller.request.CriarEspecieRequest
import br.com.petshop.pets.controller.response.EspecieResponse
import br.com.petshop.pets.controller.response.PaginacaoResponse
import br.com.petshop.pets.dto.EspecieDTO
import br.com.petshop.pets.entity.Especie
import br.com.petshop.pets.exception.EspecieCamposInvalidosException
import br.com.petshop.pets.exception.EspecieJaRegistradaException
import br.com.petshop.pets.exception.EspecieNaoEncontradaException
import br.com.petshop.pets.extensions.isPositive
import br.com.petshop.pets.mapper.toDTO
import br.com.petshop.pets.mapper.toEntity
import br.com.petshop.pets.mapper.toPaginacaoResponse
import br.com.petshop.pets.mapper.toResponse
import br.com.petshop.pets.repository.EspecieRepository
import br.com.petshop.pets.service.EspecieService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class EspecieServiceImpl(
   private val especieRepository: EspecieRepository
): EspecieService {
    override fun listarEspecies(): List<EspecieDTO> {
        return especieRepository.findAll().map { it.toDTO() }
    }

    override fun listarEspeciesComPaginacao(pagina: Int, tamanho: Int): PaginacaoResponse<EspecieResponse> {
        val pageable = PageRequest.of(pagina, tamanho)
        val resultado: Page<EspecieResponse> = especieRepository.findAll(pageable).map { it.toResponse() }
        return resultado.toPaginacaoResponse()
    }

    override fun buscarEspeciePorId(id: Int): EspecieDTO? {
        return especieRepository.findById(id)
            .orElseThrow { EspecieNaoEncontradaException("Não foi encontrada nenhum registro com o id $id") }
            .toDTO()
    }

    override fun criarEspecie(especie: CriarEspecieRequest): EspecieDTO {
        validarEspecieAntesDeSalvar(especie.toEntity())
        return especieRepository
            .save(especie.toEntity())
            .toDTO()
    }

    override fun atualizarEspecie(especie: AtualizarEspecieRequest): EspecieDTO {
        validarEspecieAntesDeAtualizar(especie.toEntity())
        return especieRepository
            .save(especie.toEntity())
            .toDTO()
    }

    override fun deletarEspecie(id: Int): Unit {
        validarEspecieAntesDeDeletar(id)
        especieRepository.deleteById(id)
    }

    fun validarEspecieAntesDeDeletar(id: Int): Unit {
        if (!id.isPositive()) {
            throw EspecieCamposInvalidosException("O valor $id é inválido para o campo id" )
        }

        if (!verificarSeJaExistePorId(id)) {
            throw EspecieNaoEncontradaException("Não foi encontrada nenhuma especie com o id $id")
        }

    }

    fun validarEspecieAntesDeSalvar(especie: Especie): Unit {
        if (verificarSeExistePorNome(especie.nome)) {
            throw EspecieJaRegistradaException("Essa especie já está cadastrada no sistema")
        }
    }

    fun validarEspecieAntesDeAtualizar(especie: Especie): Unit {
        if (!verificarSeJaExistePorId(especie.id)) {
            throw EspecieNaoEncontradaException("Não foi encontrada nenhuma especie com o id ${especie.id}")
        }
    }

    fun verificarSeExistePorNome(nome: String): Boolean {
        return especieRepository.findAllByNome(nome).isNotEmpty()
    }

    fun verificarSeJaExistePorId(id: Int): Boolean {
        return especieRepository.existsById(id)
    }
}