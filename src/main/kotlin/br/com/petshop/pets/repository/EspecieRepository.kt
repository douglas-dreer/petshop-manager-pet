package br.com.petshop.pets.repository

import br.com.petshop.pets.entity.Especie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Repository para gerenciar operações de banco de dados da entidade Especie
 *
 * Fornece métodos para realizar operações CRUD básicas e queries customizadas relacionadas a espécies de animais
 */
@Repository
interface EspecieRepository : JpaRepository<Especie, Int> {
    fun findAllByNome(name: String): List<Especie>
}