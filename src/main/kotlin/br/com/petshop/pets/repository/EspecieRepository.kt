package br.com.petshop.pets.repository

import br.com.petshop.pets.entity.Especie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EspecieRepository: JpaRepository<Especie, Int> {
    fun findAllByNome(name: String): List<Especie>
}