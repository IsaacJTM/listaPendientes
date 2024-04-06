package com.proyecto.listaPendientes.infrastructure.mapper;

import com.proyecto.listaPendientes.domain.aggregates.dto.UsuarioDTO;
import com.proyecto.listaPendientes.infrastructure.entity.UsuarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public UsuarioDTO mapToDTO(UsuarioEntity usuarioEntity){
        return modelMapper.map(usuarioEntity, UsuarioDTO.class);
    }

    public UsuarioDTO mapToEntity(UserDetails usuarioDTO){
        return modelMapper.map(usuarioDTO, UsuarioDTO.class);
    }

}
