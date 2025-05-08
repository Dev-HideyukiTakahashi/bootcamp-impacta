package br.com.impacta.boacao.mapper;

import br.com.impacta.boacao.dto.request.VoluntarioRequestDTO;
import br.com.impacta.boacao.dto.response.VoluntarioResponseDTO;
import br.com.impacta.boacao.entity.Voluntario;

public class VoluntarioMapper {

  public static Voluntario toEntity(VoluntarioRequestDTO dto) {
    Voluntario v = new Voluntario();
    v.setNomeCompleto(dto.getNomeCompleto());
    v.setCpf(dto.getCpf());
    v.setEmail(dto.getEmail());
    v.setSenha(dto.getSenha());
    v.setCep(dto.getCep());
    v.setRua(dto.getRua());
    v.setNumero(dto.getNumero());
    v.setComplemento(dto.getComplemento());
    v.setCidade(dto.getCidade());
    v.setEstado(dto.getEstado());
    v.setTelefone(dto.getTelefone());
    v.setDataNascimento(dto.getDataNascimento());
    return v;
  }

  public static VoluntarioResponseDTO toResponse(Voluntario v) {
    return new VoluntarioResponseDTO(
        v.getId(),
        v.getNomeCompleto(),
        v.getEmail()
    );
  }
}
