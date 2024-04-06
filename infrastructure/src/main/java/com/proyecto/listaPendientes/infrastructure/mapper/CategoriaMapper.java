package com.proyecto.listaPendientes.infrastructure.mapper;

import com.proyecto.listaPendientes.domain.aggregates.dto.CategoriaDTO;
import com.proyecto.listaPendientes.infrastructure.entity.CategoriaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoriaMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public CategoriaDTO mapToDTO(CategoriaEntity categoriaEntity){
        return modelMapper.map(categoriaEntity, CategoriaDTO.class);
    }

    public CategoriaEntity mapToEntity(CategoriaDTO categoriaDTO){
        return modelMapper.map(categoriaDTO, CategoriaEntity.class);
    }
}
