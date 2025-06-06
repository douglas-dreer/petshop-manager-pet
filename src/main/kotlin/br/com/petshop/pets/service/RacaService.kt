package br.com.petshop.pets.service

import br.com.petshop.pets.controller.request.AtualizarRacaRequest
import br.com.petshop.pets.controller.request.CriarRacaRequest
import br.com.petshop.pets.controller.response.PaginacaoResponse
import br.com.petshop.pets.controller.response.RacaResponse
import br.com.petshop.pets.dto.RacaDTO

/**
 * Interface de serviço para operações relacionadas a raças de animais.
 * Define os contratos para as operações CRUD e consultas sobre raças.
 */
interface RacaService {
    /**
     * Lista todas as raças cadastradas sem paginação.
     *
     * @return Lista de DTOs com informações das raças
     */
    fun listarRacasSemPaginacao(): List<RacaDTO>

    /**
     * Lista raças com suporte a paginação.
     *
     * @param pagina Índice da página a ser retornada (começando em 0)
     * @param tamanho Quantidade de itens por página
     * @return Objeto de paginação com as raças da página solicitada
     */
    fun listarRacasComPaginacao(pagina: Int, tamanho: Int): PaginacaoResponse<RacaResponse>

    /**
     * Busca uma raça pelo seu identificador.
     *
     * @param id Identificador único da raça
     * @return DTO com informações da raça encontrada ou null se não existir
     */
    fun buscarRacaPorId(id: Int): RacaDTO?

    /**
     * Cria uma nova raça no sistema.
     *
     * @param raca Objeto contendo os dados da raça a ser criada
     * @return DTO com informações da raça criada
     */
    fun criarRaca(raca: CriarRacaRequest): RacaDTO

    /**
     * Atualiza os dados de uma raça existente.
     *
     * @param raca Objeto contendo os dados atualizados da raça
     * @return DTO com informações da raça atualizada
     */
    fun atualizarRaca(raca: AtualizarRacaRequest): RacaDTO

    /**
     * Remove uma raça do sistema.
     *
     * @param id Identificador único da raça a ser removida
     */
    fun deletarRaca(id: Int)
}