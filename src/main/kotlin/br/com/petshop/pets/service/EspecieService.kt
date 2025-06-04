package br.com.petshop.pets.service

import br.com.petshop.pets.controller.request.AtualizarEspecieRequest
import br.com.petshop.pets.controller.request.CriarEspecieRequest
import br.com.petshop.pets.controller.response.EspecieResponse
import br.com.petshop.pets.controller.response.PaginacaoResponse
import br.com.petshop.pets.dto.EspecieDTO

/**
 * Interface de serviço para operações relacionadas a espécies de animais.
 * Define os contratos para as operações CRUD e consultas sobre espécies.
 */
interface EspecieService {
    /**
     * Lista todas as espécies cadastradas.
     *
     * @return Lista de DTOs com informações das espécies
     */
    fun listarEspecies(): List<EspecieDTO>
    
    /**
     * Lista espécies com suporte a paginação.
     *
     * @param pagina Índice da página a ser retornada (começando em 0)
     * @param tamanho Quantidade de itens por página
     * @return Objeto de paginação com as espécies da página solicitada
     */
    fun listarEspeciesComPaginacao(pagina: Int, tamanho: Int): PaginacaoResponse<EspecieResponse>
    
    /**
     * Busca uma espécie pelo seu identificador.
     *
     * @param id Identificador único da espécie
     * @return DTO com informações da espécie encontrada ou null se não existir
     */
    fun buscarEspeciePorId(id: Int): EspecieDTO?
    
    /**
     * Cria uma nova espécie no sistema.
     *
     * @param especie Objeto contendo os dados da espécie a ser criada
     * @return DTO com informações da espécie criada
     */
    fun criarEspecie(especie: CriarEspecieRequest): EspecieDTO
    
    /**
     * Atualiza os dados de uma espécie existente.
     *
     * @param especie Objeto contendo os dados atualizados da espécie
     * @return DTO com informações da espécie atualizada
     */
    fun atualizarEspecie(especie: AtualizarEspecieRequest): EspecieDTO
    
    /**
     * Remove uma espécie do sistema.
     *
     * @param id Identificador único da espécie a ser removida
     */
    fun deletarEspecie(id: Int)
}