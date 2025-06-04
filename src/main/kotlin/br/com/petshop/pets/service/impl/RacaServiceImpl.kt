package br.com.petshop.pets.service.impl

import br.com.petshop.pets.assembler.RacaAssembler
import br.com.petshop.pets.controller.request.AtualizarRacaRequest
import br.com.petshop.pets.controller.request.CriarRacaRequest
import br.com.petshop.pets.controller.response.EspecieResponse
import br.com.petshop.pets.controller.response.PaginacaoResponse
import br.com.petshop.pets.controller.response.RacaResponse
import br.com.petshop.pets.dto.RacaDTO
import br.com.petshop.pets.entity.Raca
import br.com.petshop.pets.exception.EspecieNaoEncontradaException
import br.com.petshop.pets.mapper.toDTO
import br.com.petshop.pets.mapper.toPaginacaoResponse
import br.com.petshop.pets.mapper.toResponse
import br.com.petshop.pets.repository.RacaRepository
import br.com.petshop.pets.service.RacaService
import br.com.petshop.pets.validate.RacaValidate
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

/**
 * Implementação do serviço responsável por gerenciar operações relacionadas às raças.
 * Esta classe fornece métodos para manipulação de dados de raças, incluindo
 * listagem, busca, criação, atualização e exclusão.
 *
 * @property repository Referência ao repositório de raças responsável pela interação com o banco de dados.
 * @property validate Componente responsável por aplicar regras de validação sobre as entidades de raças.
 * @property racaAssembler Componente utilizado para converter entre entidades de raças e objetos de requisição ou resposta.
 */
@Service
class RacaServiceImpl(
    private val repository: RacaRepository,
    private val validate: RacaValidate,
    private val racaAssembler: RacaAssembler,

): RacaService {
    /**
     * Lista todas as raças disponíveis sem aplicar paginação.
     *
     * Este método recupera todas as entidades de raças armazenadas no repositório,
     * converte-as para objetos do tipo `RacaDTO` e retorna como uma lista.
     *
     * @return Uma lista de objetos `RacaDTO` contendo os detalhes de todas as raças disponíveis.
     */
    override fun listarRacasSemPaginacao(): List<RacaDTO> {
        return repository.findAll().map { it.toDTO() }
    }

    /**
     * Lista raças disponíveis com paginação.
     *
     * Este método aplica a paginação fornecida para buscar raças no repositório,
     * mapeia os resultados para objetos do tipo `RacaResponse` e retorna a
     * resposta paginada formatada.
     *
     * @param pagina Número da página a ser retornada (baseado em zero).
     * @param tamanho Quantidade de elementos por página.
     * @return Um objeto `PaginacaoResponse<RacaResponse>` que contém os dados
     * paginados das raças.
     */
    override fun listarRacasComPaginacao(
        pagina: Int,
        tamanho: Int
    ): PaginacaoResponse<RacaResponse> {
        val pageable = PageRequest.of(pagina, tamanho)
        val resultado: Page<RacaResponse> = repository.findAll(pageable).map { it.toResponse() }
        return resultado.toPaginacaoResponse()
    }

    /**
     * Busca uma raça específica pelo seu identificador único.
     *
     * Este método tenta localizar uma raça pelo seu `id`. Caso não encontre, lança uma exceção
     * do tipo `EspecieNaoEncontradaException`. Quando encontrada, a entidade de raça é convertida
     * em um objeto `RacaDTO` antes de ser retornada.
     *
     * @param id Identificador único da raça que se deseja buscar.
     * @return Um objeto `RacaDTO` correspondente à raça encontrada ou nulo, se não houver raça.
     * @throws EspecieNaoEncontradaException Se nenhuma raça for encontrada com o identificador fornecido.
     */
    override fun buscarRacaPorId(id: Int): RacaDTO? {
        return repository.findById(id)
            .orElseThrow { EspecieNaoEncontradaException("Não foi encontrada nenhum registro com o id $id") }
            .toDTO()
    }

    /**
     * Criar uma nova raça
     * @param CriarRacaRequest request
     * @return racaDTO [RacaDTO]
     * @see [RacaRepository.save]
     */
    override fun criarRaca(raca: CriarRacaRequest): RacaDTO {
        val entity: Raca = racaAssembler.toEntity(raca)
        validate.validarRacaParaInserir(entity)
        return repository.save(entity).toDTO()
    }

    /**
     * Atualiza uma raça existente no sistema.
     *
     * Este método converte o objeto de solicitação em uma entidade `Raca`, valida os dados,
     * e persiste a entidade atualizada no banco de dados, retornando os dados atualizados como um `RacaDTO`.
     *
     * @param request Objeto contendo os dados necessários para atualizar uma raça.
     * @return Um objeto `RacaDTO` contendo as informações atualizadas da raça.
     */
    override fun atualizarRaca(request: AtualizarRacaRequest): RacaDTO {
        val entity: Raca = racaAssembler.toEntity(request)
        validate.validarRacaParaAtualizar(entity)
        return repository.save(entity).toDTO()
    }

    /**
     * Exclui permanentemente uma raça do sistema com base no seu identificador único.
     *
     * Este método valida a existência da raça e se ela pode ser removida antes de proceder com a exclusão.
     * Caso a raça não seja encontrada, uma exceção será lançada.
     *
     * @param id Identificador único da raça a ser excluída.
     * @throws RacaNaoEncontradaException Se a raça com o ID fornecido não existir.
     */
    override fun deletarRaca(id: Int) {
        validate.validarRacaParaExcluir(id)
        repository.deleteById(id)
    }

}

