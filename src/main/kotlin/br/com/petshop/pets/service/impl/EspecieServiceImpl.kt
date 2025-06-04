package br.com.petshop.pets.service.impl

import br.com.petshop.pets.controller.request.AtualizarEspecieRequest
import br.com.petshop.pets.controller.request.CriarEspecieRequest
import br.com.petshop.pets.controller.response.EspecieResponse
import br.com.petshop.pets.controller.response.PaginacaoResponse
import br.com.petshop.pets.dto.EspecieDTO
import br.com.petshop.pets.exception.EspecieNaoEncontradaException
import br.com.petshop.pets.mapper.toDTO
import br.com.petshop.pets.mapper.toEntity
import br.com.petshop.pets.mapper.toPaginacaoResponse
import br.com.petshop.pets.mapper.toResponse
import br.com.petshop.pets.repository.EspecieRepository
import br.com.petshop.pets.service.EspecieService
import br.com.petshop.pets.validate.EspecieValidate
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

/**
 * Implementação dos serviços relacionados a Especie.
 *
 * Essa classe provê operações CRUD e validações para entidades do tipo Especie.
 *
 * @property especieRepository Repositório para acessar dados de Especie
 */
@Service
class EspecieServiceImpl(
    private val especieValidate: EspecieValidate,
    private val especieRepository: EspecieRepository
) : EspecieService {
    /**
     * Lista todas as espécies cadastradas.
     *
     * @return Lista de EspecieDTO
     */
    override fun listarEspecies(): List<EspecieDTO> {
        return especieRepository.findAll().map { it.toDTO() }
    }

    /**
     * Lista espécies com paginação.
     *
     * @param pagina Número da página desejada
     * @param tamanho Quantidade de itens por página
     * @return Resposta paginada contendo as espécies
     */
    override fun listarEspeciesComPaginacao(pagina: Int, tamanho: Int): PaginacaoResponse<EspecieResponse> {
        val pageable = PageRequest.of(pagina, tamanho)
        val resultado: Page<EspecieResponse> = especieRepository.findAll(pageable).map { it.toResponse() }
        return resultado.toPaginacaoResponse()
    }

    /**
     * Busca uma espécie pelo ID.
     *
     * @param id ID da espécie
     * @return EspecieDTO encontrada
     * @throws EspecieNaoEncontradaException se a espécie não for encontrada
     */
    override fun buscarEspeciePorId(id: Int): EspecieDTO? {
        return especieRepository.findById(id)
            .orElseThrow { EspecieNaoEncontradaException("Não foi encontrada nenhum registro com o id $id") }
            .toDTO()
    }

    /**
     * Cria uma nova espécie.
     *
     * @param especie Dados da espécie a ser criada
     * @return EspecieDTO criada
     */
    override fun criarEspecie(especie: CriarEspecieRequest): EspecieDTO {
        especieValidate.validarEspecieAntesDeSalvar(especie.toEntity())
        return especieRepository
            .save(especie.toEntity())
            .toDTO()
    }

    /**
     * Atualiza uma espécie existente.
     *
     * @param especie Dados da espécie a ser atualizada
     * @return EspecieDTO atualizada
     */
    override fun atualizarEspecie(especie: AtualizarEspecieRequest): EspecieDTO {
        especieValidate.validarEspecieAntesDeAtualizar(especie.toEntity())
        return especieRepository
            .save(especie.toEntity())
            .toDTO()
    }

    /**
     * Remove uma espécie pelo ID.
     *
     * @param id ID da espécie a ser removida
     */
    override fun deletarEspecie(id: Int) {
        especieValidate.validarEspecieAntesDeDeletar(id)
        especieRepository.deleteById(id)
    }



    /**
     * Verifica se existe espécie com o ID informado.
     *
     * @param id ID a ser verificado
     * @return true se existir, false caso contrário
     */
    fun verificarSeJaExistePorId(id: Int): Boolean {
        return especieRepository.existsById(id)
    }
}