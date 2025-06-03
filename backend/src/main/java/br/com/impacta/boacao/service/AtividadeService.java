// src/main/java/br/com/impacta/boacao/service/AtividadeService.java
package br.com.impacta.boacao.service;

import java.util.List;

import br.com.impacta.boacao.dto.request.AtividadeRequestDTO;
import br.com.impacta.boacao.dto.response.AtividadeResponseDTO;
public interface AtividadeService {

    /** Cadastra uma nova atividade associada à ONG logada */
    AtividadeResponseDTO cadastrar(AtividadeRequestDTO dto);

    //Lista todas as atividades da ONG logada, paginadas */
    List<AtividadeResponseDTO> buscarTodas();

    /** Atualiza a atividade (somente se pertencer à ONG logada) */
    AtividadeResponseDTO atualizar(Integer id, AtividadeRequestDTO dto);
}
