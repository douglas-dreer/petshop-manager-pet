package br.com.petshop.pets.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "TBL_0002_ESPECIE", schema = "gerenciamento_pets")
class Especie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(name = "nome", nullable = false, unique = true)
    val nome: String,

    @Column(name = "icone")
    val icone: String? = null,

    @OneToMany(mappedBy = "especie", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val racas: List<Raca> = emptyList(),

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    val dataCriacao: LocalDateTime? = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "data_modificacao")
    val dataModificacao: LocalDateTime? = null,
)
