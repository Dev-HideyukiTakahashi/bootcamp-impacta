// src/main/java/br/com/impacta/boacao/service/AtividadeService.java
package br.com.impacta.boacao.service;

import java.util.List;

import br.com.impacta.boacao.dto.request.AtividadeRequestDTO;
import br.com.impacta.boacao.dto.response.AtividadeResponseDTO;
import br.com.impacta.boacao.dto.response.AtividadeStatusResponseDTO;
import br.com.impacta.boacao.dto.response.VoluntarioAtividadeDTO;
import br.com.impacta.boacao.entity.enums.StatusAtividade;

public interface AtividadeService {

    /**
     * Cadastra uma nova atividade associada à ONG logada
     */
    AtividadeResponseDTO cadastrar(AtividadeRequestDTO dto);

    // Lista todas as atividades da ONG logada, paginadas */
    List<AtividadeResponseDTO> buscarTodas();

    /**
     * Atualiza a atividade (somente se pertencer à ONG logada)
     */
    AtividadeResponseDTO atualizar(Integer id, AtividadeRequestDTO dto);

    /**
     * Lista todas as atividades no banco, sem filtro de ONG
     */
    List<AtividadeResponseDTO> buscarAtividades();

    /**
     * Atualiza apenas o campo statusAtividade de uma Atividade, retornando
     * somente { id, statusAtividade }. Permite apenas ANDAMENTO→CONGELADA e
     * CONGELADA→ANDAMENTO.
     */
    AtividadeStatusResponseDTO atualizarStatus(Integer id, StatusAtividade novoStatus);

    AtividadeResponseDTO getDadosAtividadePorId(Integer id);

    VoluntarioAtividadeDTO atualizarStatusCandidatura(Integer atividadeId);

}
