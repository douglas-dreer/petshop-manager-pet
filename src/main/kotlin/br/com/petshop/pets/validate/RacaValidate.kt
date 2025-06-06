package br.com.petshop.pets.validate

import br.com.petshop.pets.controller.request.AtualizarRacaRequest
import br.com.petshop.pets.entity.Raca
import br.com.petshop.pets.exception.*
import br.com.petshop.pets.extensions.isPositive
import br.com.petshop.pets.repository.RacaRepository
import br.com.petshop.pets.service.EspecieService
import org.springframework.stereotype.Component

/**
 * Classe responsável por realizar as validações relacionadas à entidade [Raca].
 *
 * As validações incluem:
 * - Verificação de duplicidade de nome de raça.
 * - Validação de integridade da espécie associada.
 * - Verificação de existência de raça por ID.
 *
 * @property repository repositório utilizado para operações com a entidade [Raca]
 * @property especieService serviço responsável pela busca de espécies
 */
@Component
class RacaValidate(
    private val repository: RacaRepository,
    private val especieService: EspecieService
) {

    /**
     * Valida uma raça antes de sua inserção no sistema.
     *
     * Realiza as seguintes validações:
     * - Se já existe uma raça com o mesmo nome.
     * - Se o ID da espécie associada é válido (positivo).
     * - Se a espécie associada existe no sistema.
     *
     * @param raca instância da raça a ser validada
     * @throws RacaJaRegistradaException se já houver uma raça com o mesmo nome
     * @throws EspecieCamposInvalidosException se o ID da espécie for inválido
     * @throws EspecieNaoEncontradaException se a espécie associada não existir
     */
    fun validarRacaParaInserir(raca: Raca) {
        validarDadosComuns(raca)
    }

    /**
     * Valida uma raça antes de sua atualização no sistema.
     *
     * Realiza as seguintes validações:
     * - Se já existe uma raça com o mesmo nome.
     * - Se o ID da espécie associada é válido (positivo).
     * - Se a espécie associada existe no sistema.
     *
     * @param raca instância da raça a ser validada
     * @throws RacaJaRegistradaException se já houver uma raça com o mesmo nome
     * @throws EspecieCamposInvalidosException se o ID da espécie for inválido
     * @throws EspecieNaoEncontradaException se a espécie associada não existir
     */
    fun validarRacaParaAtualizar(raca: Raca) {
       validarDadosComuns(raca)
    }

    /**
     * Valida a existência de uma raça antes de sua exclusão.
     *
     * @param racaId identificador da raça a ser excluída
     * @throws RacaNaoEncontradaException se a raça com o ID informado não existir
     */
    fun validarRacaParaExcluir(racaId: Int) {
        val racaExiste: Boolean = repository.existsById(racaId)

        if (!racaExiste) {
            throw RacaNaoEncontradaException("A raça com o id: $racaId não foi encontrada")
        }
    }

    /**
     * Realiza uma pré-validação para verificar se os dados informados para a alteração de uma raça são consistentes.
     *
     * Essa validação assegura que o identificador da raça a ser alterada corresponde ao identificador
     * presente na requisição de atualização.
     *
     * @param racaId Identificador único da raça a ser alterada.
     * @param atualizarRacaRequest Requisição contendo os dados para atualização da raça.
     * @throws RacaCamposInvalidosException se o identificador da raça não corresponder ao informado na requisição.
     */
    fun preValidarRacaPraAlteracao(racaId: Int, atualizarRacaRequest: AtualizarRacaRequest) {
        val codigoSaoIguais: Boolean = racaId.equals(atualizarRacaRequest.id)
        if (codigoSaoIguais.not()) {
            throw RacaCamposInvalidosException("Os dados passados não são correspondentes")
        }
    }

    private fun validarDadosComuns(raca: Raca) {
        val nomeJaCadastrado: Boolean = repository.findRacaByNome(raca.nome) != null
        val especieIdInvalido: Boolean = !raca.especie.id.isPositive()
        val especieExiste: Boolean = especieService.buscarEspeciePorId(raca.especie.id) != null

        if (nomeJaCadastrado) {
            throw RacaJaRegistradaException("Raça com o nome ${raca.nome} já está cadastrada no sistema")
        }

        if (especieIdInvalido) {
            throw EspecieCamposInvalidosException("O valor ${raca.especie.id} é inválido para o campo id")
        }

        if (!especieExiste) {
            throw EspecieNaoEncontradaException("Não foi encontrada nenhuma especie com o id ${raca.especie.id}")
        }
    }
}
