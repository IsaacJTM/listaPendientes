package com.proyecto.listaPendientes.infrastructure.mapper;

import com.proyecto.listaPendientes.domain.aggregates.dto.TareaDTO;
import com.proyecto.listaPendientes.infrastructure.entity.TareaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TareaMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public TareaDTO mapToDTO(TareaEntity tareaEntity){
        return modelMapper.map(tareaEntity, TareaDTO.class);
    }

    public TareaEntity mapToEntity(TareaDTO tareaDTO){
        return modelMapper.map(tareaDTO, TareaEntity.class);
    }
}
