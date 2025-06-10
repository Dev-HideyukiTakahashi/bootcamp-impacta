package br.com.impacta.boacao.mapper;

import org.springframework.stereotype.Component;

import br.com.impacta.boacao.dto.request.EnderecoRequestDTO;
import br.com.impacta.boacao.dto.request.EnderecoUpdateRequestDTO;
import br.com.impacta.boacao.dto.request.OngRequestDTO;
import br.com.impacta.boacao.dto.request.OngUpdateRequestDTO;
import br.com.impacta.boacao.dto.response.DadosOngResponseDTO;
import br.com.impacta.boacao.dto.response.OngResponseDTO;
import br.com.impacta.boacao.dto.response.PerfilOngResponseDTO;
import br.com.impacta.boacao.entity.Endereco;
import br.com.impacta.boacao.entity.Ong;

@Component
public class OngMapper {

    public Ong toEntity(OngRequestDTO dto) {
        Ong ong = new Ong();
        ong.setNomeEntidade(dto.getNomeEntidade());
        ong.setCnpj(dto.getCnpj());
        ong.setTelefone(dto.getTelefone());

        EnderecoRequestDTO e = dto.getEndereco();
        Endereco endereco = new Endereco();
        endereco.setPais(e.getPais());
        endereco.setEstado(e.getEstado());
        endereco.setCidade(e.getCidade());
        endereco.setCep(e.getCep());
        endereco.setRua(e.getRua());
        endereco.setNumero(e.getNumero());
        endereco.setBairro(e.getBairro());

        ong.setEndereco(endereco);
        return ong;
    }

    public OngResponseDTO toResponse(Ong ong) {
        return new OngResponseDTO(ong.getId(), ong.getNomeEntidade());
    }

    public PerfilOngResponseDTO toPerfilOngDTO(Ong ong) {
        PerfilOngResponseDTO dto = new PerfilOngResponseDTO();
        dto.setNomeEntidade(ong.getNomeEntidade());
        dto.setCnpj(ong.getCnpj());
        if (ong.getEndereco() != null) {
            dto.setCidade(ong.getEndereco().getCidade());
            dto.setEstado(ong.getEndereco().getEstado());
            dto.setRua(ong.getEndereco().getRua());
            dto.setNumero(ong.getEndereco().getNumero());
        }
        return dto;
    }

    public DadosOngResponseDTO toDadosOngDTO(Ong ong) {
        if (ong == null)
            return null;
        DadosOngResponseDTO dto = new DadosOngResponseDTO();
        dto.setNomeEntidade(ong.getNomeEntidade());
        dto.setCnpj(ong.getCnpj());
        dto.setEmail(ong.getUsuario().getEmail());
        dto.setTelefone(ong.getTelefone());
        if (ong.getEndereco() != null) {
            Endereco e = ong.getEndereco();
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

    // Atualiza telefone e endereço usando o DTO — substitui campos se não forem
    // nulos
    public void updateEntityFromDto(OngUpdateRequestDTO dto, Ong ong) {
        if (dto.getTelefone() != null && !dto.getTelefone().isBlank()) {
            ong.setTelefone(dto.getTelefone());
        }

        if (dto.getEndereco() != null) {
            updateEnderecoFromDto(dto.getEndereco(), ong.getEndereco());
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
