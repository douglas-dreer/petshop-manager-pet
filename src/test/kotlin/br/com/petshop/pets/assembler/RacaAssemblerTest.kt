package br.com.petshop.pets.assembler

import br.com.petshop.pets.controller.request.AtualizarRacaRequest
import br.com.petshop.pets.controller.request.CriarRacaRequest
import br.com.petshop.pets.dto.EspecieDTO
import br.com.petshop.pets.factory.EspecieFactory
import br.com.petshop.pets.factory.RacaFactory
import br.com.petshop.pets.service.EspecieService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class RacaAssemblerTest {

    @InjectMocks
    private lateinit var assembler: RacaAssembler

    @Mock
    private lateinit var especieService: EspecieService

    private lateinit var criarRacaRequest: CriarRacaRequest
    private lateinit var atualizarRacaRequest: AtualizarRacaRequest
    private lateinit var especieDTO: EspecieDTO

    @BeforeEach
    fun setup() {
        criarRacaRequest = RacaFactory.criarObjetoParaInserir()
        atualizarRacaRequest = RacaFactory.criarObjetoParaAtualizar()
        especieDTO = EspecieFactory.criarDTO()

    }

    @Test
    fun `deve retornar sucesso quando toEntity para inserir nova raca`() {
        whenever(especieService.buscarEspeciePorId(any<Int>())).thenReturn(especieDTO)

        val raca = assembler.toEntity(criarRacaRequest)

        assertNotNull(raca)
        assertNotNull(raca.especie)

        verify(especieService, times(1)).buscarEspeciePorId(any<Int>())
    }

    @Test
    fun `deve retornar sucesso quando toEntity para atualizar os dados da raca`() {
        whenever(especieService.buscarEspeciePorId(any<Int>())).thenReturn(especieDTO)

        val raca = assembler.toEntity(atualizarRacaRequest)

        assertNotNull(raca)
        assertNotNull(raca.especie)

        verify(especieService, times(1)).buscarEspeciePorId(any<Int>())
    }
}