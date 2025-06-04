package br.com.petshop.pets.validate

import br.com.petshop.pets.entity.Especie
import br.com.petshop.pets.exception.EspecieCamposInvalidosException
import br.com.petshop.pets.exception.EspecieJaRegistradaException
import br.com.petshop.pets.exception.EspecieNaoEncontradaException
import br.com.petshop.pets.extensions.isPositive
import br.com.petshop.pets.repository.EspecieRepository
import org.springframework.stereotype.Component

@Component
class EspecieValidate(
    private val repository: EspecieRepository
) {
    /**
     * Valida uma espécie antes de deletar.
     *
     * @param id ID da espécie
     * @throws EspecieCamposInvalidosException se o ID for inválido
     * @throws EspecieNaoEncontradaException se a espécie não existir
     */
    fun validarEspecieAntesDeDeletar(id: Int) {
        if (!id.isPositive()) {
            throw EspecieCamposInvalidosException("O valor $id é inválido para o campo id")
        }

        if (!repository.existsById(id)) {
            throw EspecieNaoEncontradaException("Não foi encontrada nenhuma especie com o id $id")
        }

    }

    /**
     * Valida uma espécie antes de salvar.
     *
     * @param especie Espécie a ser validada
     * @throws EspecieJaRegistradaException se já existir espécie com mesmo nome
     */
    fun validarEspecieAntesDeSalvar(especie: Especie) {
        if (verificarSeExistePorNome(especie.nome)) {
            throw EspecieJaRegistradaException("Essa especie já está cadastrada no sistema")
        }
    }

    /**
     * Valida uma espécie antes de atualizar.
     *
     * @param especie Espécie a ser validada
     * @throws EspecieNaoEncontradaException se a espécie não existir
     */
    fun validarEspecieAntesDeAtualizar(especie: Especie) {
        if (!repository.existsById(especie.id)) {
            throw EspecieNaoEncontradaException("Não foi encontrada nenhuma especie com o id ${especie.id}")
        }
    }

    /**
     * Verifica se existe espécie com o nome informado.
     *
     * @param nome Nome a ser verificado
     * @return true se existir, false caso contrário
     */
    fun verificarSeExistePorNome(nome: String): Boolean {
        return repository.findAllByNome(nome).isNotEmpty()
    }
}