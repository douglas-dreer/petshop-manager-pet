package br.com.petshop.pets.service.impl


import br.com.petshop.pets.controller.request.AtualizarEspecieRequest
import br.com.petshop.pets.controller.request.CriarEspecieRequest
import br.com.petshop.pets.controller.response.EspecieResponse
import br.com.petshop.pets.controller.response.PaginacaoResponse
import br.com.petshop.pets.dto.EspecieDTO
import br.com.petshop.pets.entity.Especie
import br.com.petshop.pets.exception.EspecieCamposInvalidosException
import br.com.petshop.pets.exception.EspecieJaRegistradaException
import br.com.petshop.pets.exception.EspecieNaoEncontradaException
import br.com.petshop.pets.factory.EspecieFactory
import br.com.petshop.pets.repository.EspecieRepository
import jakarta.persistence.Entity
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.Optional
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@ExtendWith(MockitoExtension::class)
class EspecieServiceImplTest() {

    @Mock
    private lateinit var repository: EspecieRepository

    @InjectMocks
    private lateinit var service: EspecieServiceImpl

    private lateinit var criarEspecieRequest: CriarEspecieRequest
    private lateinit var atualizarEspecieRequest: AtualizarEspecieRequest
    private lateinit var dto: EspecieDTO
    private lateinit var entity: Especie
    private lateinit var paginacao: Page<Especie>
    private val PAGINA: Int = 0
    private val TAMANHO: Int = 10


    @BeforeEach
    fun setup() {
        dto = EspecieFactory.criarDTO()
        entity = EspecieFactory.criarEntity()
        criarEspecieRequest = EspecieFactory.criarObjetoParaInserir()
        atualizarEspecieRequest = EspecieFactory.criarObjetoParaAtualizar()

        val pageable: Pageable = Pageable.ofSize(TAMANHO).withPage(PAGINA)
        val especies: List<Especie> = listOf(entity)
        paginacao =  PageImpl(especies, pageable, especies.size.toLong())

    }

    @Test
    fun `deve retornar sucesso quando criar uma especie`() {
        whenever(repository.save(any())).thenReturn(entity)
        whenever(repository.findAllByNome(anyString())).thenReturn(emptyList())

        val result = service.criarEspecie(criarEspecieRequest)

        assertNotNull(result)

        verify(repository, times(1)).findAllByNome(anyString())
        verify(repository, times(1)).save(any())
    }

    @Test
    fun `deve retornar EspecieJaRegistradaException quando salva com um nome ja existente `() {
        val especieList: List<Especie> = listOf(entity)
        whenever(repository.findAllByNome(anyString())).thenReturn(especieList)

        val resultado = assertThrows<EspecieJaRegistradaException> {
            service.criarEspecie(criarEspecieRequest)
        }

        verify(repository, times(1)).findAllByNome(anyString())
    }

    @Test
    fun `deve retornar sucesso quando listar sem paginacao especies`() {
        whenever(repository.findAll()).thenReturn(listOf(entity))

        val result = service.listarEspecies()

        assertNotNull(result)
        assertFalse(result.isEmpty())

        verify(repository, times(1)).findAll()
    }

    @Test
    fun `deve retornar sucesso quando listar com paginacao especies` () {
        whenever(repository.findAll(any<Pageable>())).thenReturn(paginacao)

        val resultado: PaginacaoResponse<EspecieResponse> = service.listarEspeciesComPaginacao(PAGINA, TAMANHO)

        assertNotNull(resultado)
        assertFalse(resultado.conteudo.isEmpty())

        verify(repository, times(1)).findAll(any<Pageable>())
    }

    @Test
    fun `deve retornar sucesso quando buscar especie por id`() {
        val id = entity.id
        whenever(repository.findById(any())).thenReturn(Optional.of(entity))

        val resultado = service.buscarEspeciePorId(id)

        assertNotNull(resultado)
        assertEquals(id, resultado.id)

        verify(repository, times(1)).findById(any())
    }

    @Test
    fun `deve retornar EspecieNaoEncontada qunado buscar por id e nao encontrar` () {
        val id = entity.id
        whenever(repository.findById(any())).thenReturn(Optional.empty())

        assertThrows<EspecieNaoEncontradaException> {
            service.buscarEspeciePorId(id)
        }

        verify(repository, times(1)).findById(any())
    }

    @Test
    fun `deve retornar sucesso quando atualizar especie`() {
        whenever(repository.existsById(any())).thenReturn(true)
        whenever(repository.save(any())).thenReturn(entity)

        val resultado = service.atualizarEspecie(atualizarEspecieRequest)

        assertNotNull(resultado)
        assertEquals(atualizarEspecieRequest.nome, resultado.nome)

        verify(repository, times(1)).existsById(any())
        verify(repository, times(1)).save(any())
    }

    @Test
    fun `deve retornar EspecieNaoEncontrada quando atualizar especie com id nao encontrado` () {
        whenever(repository.existsById(any())).thenReturn(false)

        assertThrows<EspecieNaoEncontradaException> {
            service.atualizarEspecie(atualizarEspecieRequest)
        }

        verify(repository, times(1)).existsById(any())
    }

    @Test
    fun `deve retornar sucesso quando deletar especie por id`() {
        val id: Int = entity.id
        whenever(repository.existsById(any())).thenReturn(true)
        doNothing().whenever(repository).deleteById(any<Int>())

        service.deletarEspecie(id)

        verify(repository, times(1)).existsById(any<Int>())
        verify(repository, times(1)).deleteById(any<Int>())
    }

    @Test
    fun `deve retornar EspecieNaoEncontrada quando deletar especie por id e nao encontrar` () {
        val id: Int = entity.id
        whenever(repository.existsById(any())).thenReturn(false)

        assertThrows<EspecieNaoEncontradaException> {
            service.deletarEspecie(id)
        }

        verify(repository, times(1)).existsById(any<Int>())
    }

    @Test
    fun `deve retornar EspecieCamposInvalidosException quando o codigo id e invalido`() {
        val id: Int = -1

        assertThrows<EspecieCamposInvalidosException> {
            service.deletarEspecie(id)
        }

        verify(repository, times(0)).deleteById(any<Int>())
    }



}