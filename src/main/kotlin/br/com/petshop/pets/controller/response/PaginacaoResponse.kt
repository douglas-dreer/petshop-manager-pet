package br.com.petshop.pets.controller.response

/**
 * Classe de resposta para paginação de dados.
 *
 * @param T O tipo de dado que será retornado na lista paginada
 * @property conteudo A lista de dados da página atual
 * @property paginaAtual O número da página atual
 * @property totalPaginas O número total de páginas
 * @property totalRegistros O número total de registros
 */
data class PaginacaoResponse<T>(
    val conteudo: List<T>,
    val paginaAtual: Int,
    val totalPaginas: Int,
    val totalRegistros: Long,
)
