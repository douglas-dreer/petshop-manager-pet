package br.com.petshop.pets.controller.request

/**
 * Representa uma requisição para atualização de espécie.
 *
 * @property id Identificador da espécie a ser atualizada
 * @property nome Novo nome para a espécie
 * @property icone Novo ícone para a espécie (opcional)
 */
data class AtualizarEspecieRequest(
    val id: Int,
    val nome: String,
    val icone: String? = null,
)
