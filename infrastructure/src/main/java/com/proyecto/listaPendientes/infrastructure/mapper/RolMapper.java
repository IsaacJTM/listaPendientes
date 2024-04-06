package com.proyecto.listaPendientes.infrastructure.mapper;

import com.proyecto.listaPendientes.domain.aggregates.dto.RolDTO;
import com.proyecto.listaPendientes.infrastructure.entity.RolEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RolMapper {
    private static final  ModelMapper modelMapper = new ModelMapper();

    public RolDTO mapToDto(RolEntity rolEntity){
        return modelMapper.map(rolEntity,RolDTO.class);
    }
}
