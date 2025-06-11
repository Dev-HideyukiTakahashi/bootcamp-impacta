package br.com.impacta.boacao.mapper;

import org.springframework.stereotype.Component;

import br.com.impacta.boacao.dto.request.EnderecoRequestDTO;
import br.com.impacta.boacao.dto.request.EnderecoUpdateRequestDTO;
import br.com.impacta.boacao.dto.request.VoluntarioRequestDTO;
import br.com.impacta.boacao.dto.request.VoluntarioUpdateRequestDTO;
import br.com.impacta.boacao.dto.response.DadosVoluntarioResponseDTO;
import br.com.impacta.boacao.dto.response.PerfilVoluntarioResponseDTO;
import br.com.impacta.boacao.dto.response.VoluntarioResponseDTO;
import br.com.impacta.boacao.entity.Endereco;
import br.com.impacta.boacao.entity.Voluntario;

@Component
public class VoluntarioMapper {

    public Voluntario toEntity(VoluntarioRequestDTO dto) {
        Voluntario v = new Voluntario();
        v.setNomeCompleto(dto.getNomeCompleto());
        v.setCpf(dto.getCpf());
        v.setTelefone(dto.getTelefone());
        v.setDataNascimento(dto.getDataNascimento());

        EnderecoRequestDTO e = dto.getEndereco();
        Endereco endereco = new Endereco();
        endereco.setPais(e.getPais());
        endereco.setEstado(e.getEstado());
        endereco.setCidade(e.getCidade());
        endereco.setCep(e.getCep());
        endereco.setRua(e.getRua());
        endereco.setNumero(e.getNumero());
        endereco.setBairro(e.getBairro());
        v.setEndereco(endereco);
        return v;
    }

    public VoluntarioResponseDTO toResponse(Voluntario v) {
        return new VoluntarioResponseDTO(v.getId(), v.getNomeCompleto());
    }

    public PerfilVoluntarioResponseDTO toPerfilVoluntarioDTO(Voluntario v) {
        PerfilVoluntarioResponseDTO dto = new PerfilVoluntarioResponseDTO();
        String[] parts = v.getNomeCompleto().split(" ");
        dto.setFullName(v.getNomeCompleto());
        dto.setFirstName(parts.length > 0 ? parts[0] : v.getNomeCompleto());
        if (v.getEndereco() != null) {
            dto.setCidade(v.getEndereco().getCidade());
            dto.setEstado(v.getEndereco().getEstado());
        }
        return dto;
    }

    public DadosVoluntarioResponseDTO toDadosVoluntarioDTO(Voluntario v) {
        if (v == null)
            return null;
        DadosVoluntarioResponseDTO dto = new DadosVoluntarioResponseDTO();
        dto.setNomeCompleto(v.getNomeCompleto());
        dto.setCpf(v.getCpf());
        dto.setEmail(v.getUsuario().getEmail());
        dto.setTelefone(v.getTelefone());
        if (v.getDataNascimento() != null) {
            dto.setDataNascimento(v.getDataNascimento().toString());
        }
        if (v.getEndereco() != null) {
            Endereco e = v.getEndereco();
            dto.setPais(e.getPais());
            dto.setCep(e.getCep());
            dto.setEstado(e.getEstado());
            dto.setCidade(e.getCidade());
            dto.setBairro(e.getBairro());
            dto.setRua(e.getRua());
            dto.setNumero(e.getNumero());
        }
        return dto;
    }

    public void updateEntityFromDto(VoluntarioUpdateRequestDTO dto, Voluntario v) {
        if (dto.getTelefone() != null && !dto.getTelefone().isBlank()) {
            v.setTelefone(dto.getTelefone());
        }

        if (dto.getEndereco() != null) {
            updateEnderecoFromDto(dto.getEndereco(), v.getEndereco());
        }
    }

    // Atualiza somente os campos do endereço, ignorando valores nulos para não
    // sobrescrever
    public void updateEnderecoFromDto(EnderecoUpdateRequestDTO dto, Endereco endereco) {
        if (dto.getCep() != null)
            endereco.setCep(dto.getCep());
        if (dto.getEstado() != null)
            endereco.setEstado(dto.getEstado());
        if (dto.getCidade() != null)
            endereco.setCidade(dto.getCidade());
        if (dto.getBairro() != null)
            endereco.setBairro(dto.getBairro());
        if (dto.getRua() != null)
            endereco.setRua(dto.getRua());
        if (dto.getNumero() != null)
            endereco.setNumero(dto.getNumero());
    }
}
