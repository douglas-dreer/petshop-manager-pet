package br.com.petshop.pets.repository

import br.com.petshop.pets.entity.Raca
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RacaRepository: JpaRepository<Raca, Int> {
    fun findRacaByNome(nome: String): Raca?
}