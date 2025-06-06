package br.com.petshop.pets.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "TBL_0001_RACAS", schema = "gerenciamento_pets")
class Raca(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @Column(name = "nome", nullable = false, unique = true)
    val nome: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "especie_id", nullable = false)
    val especie: Especie,

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "data_modificacao")
    val dataModificacao: LocalDateTime? = null,
) {
}