package com.proyecto.listaPendientes.domain.port.out;

import com.proyecto.listaPendientes.domain.aggregates.dto.UsuarioDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.SignInRequest;
import com.proyecto.listaPendientes.domain.aggregates.request.SignUpRequest;
import com.proyecto.listaPendientes.domain.aggregates.response.AuthenticationResponse;

public interface AuthenticationServiceOut {
    UsuarioDTO signUpUser(SignUpRequest signUpRequest);
    UsuarioDTO signUpAdmin(SignUpRequest signUpRequest);
    AuthenticationResponse signIn(SignInRequest signInRequest);
}
