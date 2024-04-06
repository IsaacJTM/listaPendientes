package com.proyecto.listaPendientes.application.controller;

import com.proyecto.listaPendientes.domain.aggregates.dto.UsuarioDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.SignInRequest;
import com.proyecto.listaPendientes.domain.aggregates.request.SignUpRequest;
import com.proyecto.listaPendientes.domain.aggregates.response.AuthenticationResponse;
import com.proyecto.listaPendientes.domain.port.in.AuthenticationServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/autenticacion")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationServiceIn authenticationServiceIn;
    @PostMapping("/signUpUser")
    public ResponseEntity<UsuarioDTO> signUpUser(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationServiceIn.signUpUser(signUpRequest));
    }

    @PostMapping("/signUpAdmin")
    public ResponseEntity<UsuarioDTO> signUpAdmin(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationServiceIn.signUpAdmin(signUpRequest));
    }

    @PostMapping("/signIn")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationServiceIn.signIn(signInRequest));
    }
}
