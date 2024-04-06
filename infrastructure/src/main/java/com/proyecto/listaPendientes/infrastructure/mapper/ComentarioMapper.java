package com.proyecto.listaPendientes.infrastructure.mapper;


import com.proyecto.listaPendientes.domain.aggregates.dto.ComentarioDTO;
import com.proyecto.listaPendientes.infrastructure.entity.ComentarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ComentarioMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public ComentarioDTO mapToDTO(ComentarioEntity comentarioEntity){
        return modelMapper.map(comentarioEntity, ComentarioDTO.class);
    }

    public ComentarioEntity mapToEntity(ComentarioDTO comentarioDTO){
        return modelMapper.map(comentarioDTO, ComentarioEntity.class);
    }
}
