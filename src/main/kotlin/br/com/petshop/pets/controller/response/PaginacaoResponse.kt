package br.com.petshop.pets.controller.response

data class PaginacaoResponse<T>(
    val conteudo: List<T>,
    val paginaAtual: Int,
    val totalPaginas: Int,
    val totalRegistros: Long,
)
