package br.com.petshop.pets.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

/**
 * Entidade que representa uma espécie de animal.
 *
 * @property id Identificador único da espécie
 * @property nome Nome da espécie, valor único na base de dados
 * @property icone Caminho para o ícone da espécie, opcional
 * @property racas Lista de raças pertencentes a esta espécie
 * @property dataCriacao Data de criação do registro, preenchida automaticamente
 * @property dataModificacao Data da última modificação do registro, atualizada automaticamente
 */
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