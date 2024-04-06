package com.proyecto.listaPendientes.domain.port.out;

import com.proyecto.listaPendientes.domain.aggregates.dto.UsuarioDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestUsuario;
import com.proyecto.listaPendientes.domain.aggregates.request.SignUpRequest;
import com.proyecto.listaPendientes.domain.aggregates.response.ResponseBase;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UsuarioServiceOut {

    Optional<UsuarioDTO> getUsuarioOut(Long id);
    UsuarioDTO updateUsuarioOut(Long id, RequestUsuario requestUsuario);
    UsuarioDTO deleteUsuarioOut(Long id);
    List<UsuarioDTO> obtenerTodasOut();
    UserDetailsService userDetailsService();
}
