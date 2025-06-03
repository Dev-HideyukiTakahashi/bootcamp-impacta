package br.com.impacta.boacao.factory;

import java.time.LocalDateTime;
import java.util.List;

import br.com.impacta.boacao.dto.request.AtividadeRequestDTO;
import br.com.impacta.boacao.dto.request.AtividadeStatusRequestDTO;
import br.com.impacta.boacao.dto.response.AtividadeResponseDTO;
import br.com.impacta.boacao.dto.response.AtividadeStatusResponseDTO;
import br.com.impacta.boacao.entity.Atividade;
import br.com.impacta.boacao.entity.Tag;
import br.com.impacta.boacao.entity.enums.PeriodoAtividade;
import br.com.impacta.boacao.entity.enums.StatusAtividade;

public class AtividadeFactory {

    /**
     * Entidade completa, já com Tag e título.
     */
    public static Atividade createAtividade() {
        Tag tag = new Tag();
        tag.setId(100);
        tag.setNome("Educação Digital");

        Atividade atividade = new Atividade();
        atividade.setId(1);
        atividade.setNome("Aula de Informática");
        atividade.setDescricao("Aulas de informática básica para idosos");
        atividade.setPeriodo(PeriodoAtividade.TARDE);
        atividade.setCargaHorariaDiaria("4 horas");
        atividade.setEnderecoCompleto("Rua das Flores, 123");
        atividade.setPossuiCertificacao(true);
        atividade.setStatusAtividade(StatusAtividade.ANDAMENTO);
        atividade.setCriadoEm(LocalDateTime.now());
        atividade.setDataAtividade(LocalDateTime.now().plusDays(1));
        atividade.setTag(tag);
        atividade.setTitulo(tag.getNome());
        return atividade;
    }

    /**
     * DTO de entrada (request).
     */
    public static AtividadeRequestDTO createAtividadeRequestDTO() {
        AtividadeRequestDTO dto = new AtividadeRequestDTO();
        dto.setNome("Aula de Informática");
        dto.setDescricao("Aulas de informática básica para idosos");
        dto.setPeriodo(PeriodoAtividade.TARDE);
        dto.setCargaHorariaDiaria("4 horas");
        dto.setEnderecoCompleto("Rua das Flores, 123");
        dto.setPossuiCertificacao(true);
        dto.setStatusAtividade(StatusAtividade.ANDAMENTO);
        dto.setDataAtividade(LocalDateTime.now().plusDays(1));
        // implicitamente a ONG logada:
        dto.setIdOng(1);
        // tag escolhida:
        dto.setIdTag(100);
        return dto;
    }

    /**
     * DTO só para trocar status.
     */
    public static AtividadeStatusRequestDTO createAtividadeStatusRequestDTO() {
        return new AtividadeStatusRequestDTO(1, StatusAtividade.ENCERRADA);
    }

    /**
     * DTO de saída “normal” — observe 13 parâmetros na mesma
     * ordem do seu construtor em AtividadeResponseDTO.
     */
    public static AtividadeResponseDTO createAtividadeResponseDTO() {
        return new AtividadeResponseDTO(
            1,                                     // id
            "Aula de Informática",                // nome
            PeriodoAtividade.TARDE,               // periodo
            "4 horas",                            // cargaHorariaDiaria
            "Rua das Flores, 123",                // enderecoCompleto
            true,                                 // possuiCertificacao
            "Aulas de informática básica para idosos", // descricao
            StatusAtividade.ANDAMENTO,            // statusAtividade
            LocalDateTime.now().plusDays(1),      // dataAtividade
            1,                                    // idOng
            100,                                  // idTag
            "Educação Digital"                    // titulo  (nome da tag)
        );
    }

    /**
     * DTO de saída para listagem da ONG — observe 10 parâmetros
     * (o idOng é o último).
    
    public static AtividadeOngResponseDTO createAtividadeOngResponseDTO() {
        return new AtividadeOngResponseDTO(
            1,                                     // id
            "Aula de Informática",                // nome
            5L,                                   // total aprovados (COUNT)
            "4 horas",                            // cargaHorariaDiaria
            PeriodoAtividade.TARDE,               // periodo
            StatusAtividade.ANDAMENTO,            // statusAtividade
            "Aulas de informática básica para idosos", // descricao
            LocalDateTime.now().plusDays(1),      // dataAtividade
            1                                     // idOng
        );
    } */

    /**
     * DTO de saída só de status.
     */
    public static AtividadeStatusResponseDTO createAtividadeStatusResponseDTO() {
        return new AtividadeStatusResponseDTO(1, StatusAtividade.ENCERRADA);
    }

    /**
     * Duas atividades pra lista.
     */
    public static List<Atividade> createAtividadeList() {
        Atividade a1 = createAtividade();
        Atividade a2 = new Atividade();
        a2.setId(2);
        a2.setNome("Oficina de Artes");
        a2.setDescricao("Oficina de pintura para iniciantes");
        a2.setPeriodo(PeriodoAtividade.MANHA);
        a2.setCargaHorariaDiaria("3 horas");
        a2.setEnderecoCompleto("Rua das Palmeiras, 45");
        a2.setPossuiCertificacao(false);
        a2.setStatusAtividade(StatusAtividade.ANDAMENTO);
        a2.setCriadoEm(LocalDateTime.now());
        a2.setDataAtividade(LocalDateTime.now().plusDays(2));
        a2.setTag(a1.getTag());
        a2.setTitulo(a1.getTitulo());
        return List.of(a1, a2);
    }
}
