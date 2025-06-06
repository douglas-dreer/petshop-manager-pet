package br.com.petshop.pets.validate

import br.com.petshop.pets.controller.request.AtualizarRacaRequest
import br.com.petshop.pets.dto.EspecieDTO
import br.com.petshop.pets.entity.Raca
import br.com.petshop.pets.exception.EspecieCamposInvalidosException
import br.com.petshop.pets.exception.EspecieNaoEncontradaException
import br.com.petshop.pets.exception.RacaCamposInvalidosException
import br.com.petshop.pets.exception.RacaJaRegistradaException
import br.com.petshop.pets.factory.EspecieFactory
import br.com.petshop.pets.factory.RacaFactory
import br.com.petshop.pets.mapper.toEntity
import br.com.petshop.pets.repository.RacaRepository
import br.com.petshop.pets.service.impl.EspecieServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
class RacaValidateTest {
    @InjectMocks
    private lateinit var validate: RacaValidate

    @Mock
    private lateinit var racaRepository: RacaRepository

    @Mock
    private lateinit var especieService: EspecieServiceImpl

    private lateinit var racaEntity: Raca
    private lateinit var especieDTO: EspecieDTO
    private lateinit var atualizarRacaRequest: AtualizarRacaRequest

    @BeforeEach
    fun setup() {
        racaEntity = RacaFactory.criarEntity()
        especieDTO = EspecieFactory.criarDTO()
        atualizarRacaRequest = RacaFactory.criarObjetoParaAtualizar()
    }


    @Test
    fun `nao deve retornar erro ao validarRacaInserir`() {
        whenever(racaRepository.findRacaByNome(anyString())).thenReturn(null)
        whenever(especieService.buscarEspeciePorId(any<Int>())).thenReturn(especieDTO)

        validate.validarRacaParaInserir(racaEntity)

        verify(racaRepository, times(1)).findRacaByNome(anyString())
        verify(especieService, times(1)).buscarEspeciePorId(any<Int>())
    }

    @Test
    fun `nao deve retornar erro ao validarRacaAtualizar`() {
        whenever(racaRepository.findRacaByNome(anyString())).thenReturn(null)
        whenever(especieService.buscarEspeciePorId(any<Int>())).thenReturn(especieDTO)

        validate.validarRacaParaAtualizar(racaEntity)

        verify(racaRepository, times(1)).findRacaByNome(anyString())
        verify(especieService, times(1)).buscarEspeciePorId(any<Int>())
    }

    @Test
    fun `deve retornar RacaJaRegistradaException ao validarRacaInserir com nome duplicado`() {
        whenever(racaRepository.findRacaByNome(anyString())).thenReturn(racaEntity)
        whenever(especieService.buscarEspeciePorId(any<Int>())).thenReturn(especieDTO)

        assertThrows<RacaJaRegistradaException> { validate.validarRacaParaInserir(racaEntity) }

        verify(racaRepository, times(1)).findRacaByNome(anyString())
        verify(especieService, times(1)).buscarEspeciePorId(any<Int>())
    }

    @Test
    fun `deve retornar EspecieNaoEncontradaException ao validarRacaInserir com dados invalidos`() {
        whenever(racaRepository.findRacaByNome(anyString())).thenReturn(null)
        whenever(especieService.buscarEspeciePorId(any<Int>())).thenReturn(null)

        assertThrows<EspecieNaoEncontradaException> { validate.validarRacaParaInserir(racaEntity) }

        verify(racaRepository, times(1)).findRacaByNome(anyString())
        verify(especieService, times(1)).buscarEspeciePorId(any<Int>())
    }

    @Test
    fun `deve retornar EspecieCamposInvalidosException ao validarRacaAtualizar com dados invalidos`() {
        especieDTO = EspecieFactory.criarDTOComId(-1)
        val racaInvalido = RacaFactory.criarEntityComEspecie(especieDTO.toEntity())

        whenever(racaRepository.findRacaByNome(anyString())).thenReturn(null)
        whenever(especieService.buscarEspeciePorId(any<Int>())).thenReturn(especieDTO)

        assertThrows<EspecieCamposInvalidosException> { validate.validarRacaParaAtualizar(racaInvalido) }

        verify(racaRepository, times(1)).findRacaByNome(anyString())
        verify(especieService, times(1)).buscarEspeciePorId(any<Int>())
    }

    @Test
    fun `nao deve retornar erro ao preValidarRacaParaAlteracao`() {
        val racaId = 1

        validate.preValidarRacaPraAlteracao(racaId, atualizarRacaRequest)
    }

    @Test
    fun `deve retornar RacaCamposInvalidosException ao preValidarRacaParaAlteracao com racaId invalida`() {
        val racaId = 2

        assertThrows<RacaCamposInvalidosException> { validate.preValidarRacaPraAlteracao(racaId, atualizarRacaRequest) }
    }

}