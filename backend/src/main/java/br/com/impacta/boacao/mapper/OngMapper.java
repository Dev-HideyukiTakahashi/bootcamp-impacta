package br.com.impacta.boacao.mapper;

import br.com.impacta.boacao.dto.request.OngRequestDTO;
import br.com.impacta.boacao.dto.response.OngResponseDTO;
import br.com.impacta.boacao.entity.Ong;

public class OngMapper {

    // Converte OngRequestDTO para entidade Ong
    public static Ong toEntity(OngRequestDTO dto) {
       Ong o = new Ong(); 
        o.setNomeOng(dto.getNomeOng());
        o.setCnpj(dto.getCnpj());
        o.setTelefone(dto.getTelefone());
        o.setEndereco(dto.getEndereco());
        //o.setCep(dto.getCep());
        return o;
    }

    // Converte entidade Ong para OngResponseDTO
    public static OngResponseDTO toResponse(Ong o) {
        return new OngResponseDTO(
            o.getId(),
            o.getNomeOng(),
            o.getCnpj()
        );
    }
}

