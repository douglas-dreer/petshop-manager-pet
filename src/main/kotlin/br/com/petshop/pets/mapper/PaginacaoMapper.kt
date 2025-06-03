package br.com.petshop.pets.mapper

import br.com.petshop.pets.controller.response.PaginacaoResponse
import org.springframework.data.domain.Page

fun <T> Page<T>.toPaginacaoResponse(): PaginacaoResponse<T> = PaginacaoResponse(
    conteudo = this.content,
    paginaAtual = this.pageable.pageNumber,
    totalPaginas = this.totalPages,
    totalRegistros = this.totalElements
)
