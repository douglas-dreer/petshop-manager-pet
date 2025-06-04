package br.com.petshop.pets.validate

import br.com.petshop.pets.entity.Raca
import br.com.petshop.pets.exception.EspecieCamposInvalidosException
import br.com.petshop.pets.exception.EspecieNaoEncontradaException
import br.com.petshop.pets.exception.RacaJaRegistradaException
import br.com.petshop.pets.exception.RacaNaoEncontradaException
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
}
