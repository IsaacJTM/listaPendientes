package com.proyecto.listaPendientes.domain.impl;

import com.proyecto.listaPendientes.domain.aggregates.dto.UsuarioDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.SignInRequest;
import com.proyecto.listaPendientes.domain.aggregates.request.SignUpRequest;
import com.proyecto.listaPendientes.domain.aggregates.response.AuthenticationResponse;
import com.proyecto.listaPendientes.domain.port.in.AuthenticationServiceIn;
import com.proyecto.listaPendientes.domain.port.out.AuthenticationServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationServiceIn {
    private final AuthenticationServiceOut authenticationServiceOut;

    @Override
    public UsuarioDTO signUpUser(SignUpRequest signUpRequest) {
        return authenticationServiceOut.signUpUser(signUpRequest);
    }

    @Override
    public UsuarioDTO signUpAdmin(SignUpRequest signUpRequest) {
        return authenticationServiceOut.signUpAdmin(signUpRequest);
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        return authenticationServiceOut.signIn(signInRequest);
    }
}
