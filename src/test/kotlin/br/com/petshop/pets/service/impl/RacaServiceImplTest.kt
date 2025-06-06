package br.com.petshop.pets.service.impl

import br.com.petshop.pets.assembler.RacaAssembler
import br.com.petshop.pets.controller.request.AtualizarRacaRequest
import br.com.petshop.pets.controller.request.CriarRacaRequest
import br.com.petshop.pets.dto.RacaDTO
import br.com.petshop.pets.entity.Raca
import br.com.petshop.pets.exception.EspecieNaoEncontradaException
import br.com.petshop.pets.factory.RacaFactory
import br.com.petshop.pets.repository.RacaRepository
import br.com.petshop.pets.validate.RacaValidate
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class RacaServiceImplTest() {
    @Mock
    private lateinit var repository: RacaRepository

    @Mock
    private lateinit var validate: RacaValidate

    @Mock
    private lateinit var racaAssembler: RacaAssembler

    @InjectMocks
    private lateinit var service: RacaServiceImpl

    private lateinit var entity: Raca
    private lateinit var criarRacaRequest: CriarRacaRequest
    private lateinit var atualizarRacaRequest: AtualizarRacaRequest
    private lateinit var paginacao: Page<Raca>
    private val PAGINA: Int = 0
    private val TAMANHO: Int = 10


    @BeforeEach
    fun setup() {
        entity = RacaFactory.criarEntity()
        val pagable: Pageable = Pageable
            .ofSize(TAMANHO)
            .withPage(PAGINA)
        val racas: List<Raca> = listOf(entity)
        paginacao = PageImpl(racas, pagable, racas.size.toLong())

        criarRacaRequest = RacaFactory.criarObjetoParaInserir()
        atualizarRacaRequest = RacaFactory.criarObjetoParaAtualizar()
    }

    @Test
    fun `deve retornar uma raca`() {
        val id = entity.id
        whenever(repository.findById(any())).thenReturn(java.util.Optional.of(entity))

        val resultado = service.buscarRacaPorId(id)

        assertNotNull(resultado)
        assertEquals(id, resultado?.id)

        verify(repository, times(1)).findById(any())
    }

    @Test
    fun `deve retornar EspecieNaoEncontrada quando buscar por id e nao encontrar` () {
        val id = entity.id
        whenever(repository.findById(any())).thenReturn(java.util.Optional.empty())

        assertThrows<EspecieNaoEncontradaException> { service.buscarRacaPorId(id) }

        verify(repository, times(1)).findById(any())
    }

    @Test
    fun `deve retornar sucesso quando buscar uma lista de racas` () {
        whenever(repository.findAll()).thenReturn(listOf(entity))

        val resulatadoLista = service.listarRacasSemPaginacao()

        assertNotNull(resulatadoLista)
        assertFalse(resulatadoLista.isEmpty())

        verify(repository, times(1)).findAll()

    }

    @Test
    fun `deve retornar sucesso quando buscar uma lista de racas com paginação`() {
        whenever(repository.findAll(any<Pageable>())).thenReturn(paginacao)

        val resultado = service.listarRacasComPaginacao(PAGINA, TAMANHO)

        assertNotNull(resultado)
        assertFalse(resultado.conteudo.isEmpty())

        verify(repository, times(1)).findAll(any<Pageable>())
    }

    @Test
    fun `deve retornar sucesso quando salvar uma raca`() {

        whenever(racaAssembler.toEntity(any<CriarRacaRequest>())).thenReturn(entity)

        doNothing().whenever(validate).validarRacaParaInserir(any())
        whenever(repository.save(any<Raca>())).thenReturn(entity)

        val resultado: RacaDTO = service.criarRaca(criarRacaRequest)

        assertNotNull(resultado)

        verify(racaAssembler, times(1)).toEntity(any<CriarRacaRequest>())
        verify(validate, times(1)).validarRacaParaInserir(any())
        verify(repository, times(1)).save(any())
    }

    @Test
    fun `deve retornar sucesso quando atualizar uma raca`() {
        val id = entity.id

        whenever(racaAssembler.toEntity(any<AtualizarRacaRequest>())).thenReturn(entity)
        doNothing().whenever(validate).validarRacaParaAtualizar(any())
        whenever(repository.save(any<Raca>())).thenReturn(entity)

        val resultado: RacaDTO = service.atualizarRaca(atualizarRacaRequest)

        assertNotNull(resultado)

        verify(racaAssembler, times(1)).toEntity(any<AtualizarRacaRequest>())
        verify(validate, times(1)).validarRacaParaAtualizar(any())
        verify(repository, times(1)).save(any())

    }

    @Test
    fun `deve retornar sucesso quando deletar uma raca`() {
        val id = entity.id

        doNothing().whenever(validate).validarRacaParaExcluir(any())
        doNothing().whenever(repository).deleteById(any<Int>())

        service.deletarRaca(id)

        verify(validate, times(1)).validarRacaParaExcluir(any())
        verify(repository, times(1)).deleteById(any<Int>())
    }
}
