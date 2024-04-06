package com.proyecto.listaPendientes.domain.port.in;

import com.proyecto.listaPendientes.domain.aggregates.dto.TareaDTO;
import com.proyecto.listaPendientes.domain.aggregates.dto.UsuarioDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestUsuario;
import com.proyecto.listaPendientes.domain.aggregates.request.SignUpRequest;
import com.proyecto.listaPendientes.domain.aggregates.response.ResponseBase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UsuarioServiceIn {
    Optional<UsuarioDTO> getUsuarioIn(Long id);
    UsuarioDTO updateUsuarioIn(Long id, RequestUsuario requestUsuario);
    UsuarioDTO deleteUsuarioIn(Long id);
    List<UsuarioDTO> obtenerTodasIn();
    UserDetailsService userDetailsServiceIn();
}
