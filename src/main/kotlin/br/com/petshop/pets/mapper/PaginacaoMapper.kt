package br.com.petshop.pets.mapper

import br.com.petshop.pets.controller.response.PaginacaoResponse
import org.springframework.data.domain.Page

/**
 * Converte um objeto [Page] de tipo genérico [T] em um objeto [PaginacaoResponse].
 *
 * @return Uma nova instância de [PaginacaoResponse] contendo:
 * - [PaginacaoResponse.conteudo] lista de elementos da página atual
 * - [PaginacaoResponse.paginaAtual] número da página atual (iniciada em 0)
 * - [PaginacaoResponse.totalPaginas] número total de páginas
 * - [PaginacaoResponse.totalRegistros] número total de registros
 */
fun <T> Page<T>.toPaginacaoResponse(): PaginacaoResponse<T> = PaginacaoResponse(
    conteudo = this.content,
    paginaAtual = this.pageable.pageNumber,
    totalPaginas = this.totalPages,
    totalRegistros = this.totalElements
)
