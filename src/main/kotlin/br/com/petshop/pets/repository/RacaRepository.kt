package br.com.petshop.pets.repository

import br.com.petshop.pets.entity.Raca
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Repository para gerenciar operações de banco de dados da entidade Raca
 *
 * Fornece métodos para realizar operações CRUD básicas e queries customizadas relacionadas a raças de animais
 */
@Repository
interface RacaRepository : JpaRepository<Raca, Int> {
    fun findRacaByNome(nome: String): Raca?
}