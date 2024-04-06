package com.proyecto.listaPendientes.infrastructure.adapter;

import com.proyecto.listaPendientes.domain.aggregates.dto.UsuarioDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.SignUpRequest;
import com.proyecto.listaPendientes.domain.aggregates.response.ResponseReniec;
import com.proyecto.listaPendientes.domain.port.out.JWTServiceOut;
import com.proyecto.listaPendientes.domain.port.out.UsuarioServiceOut;
import com.proyecto.listaPendientes.infrastructure.entity.RolEntity;
import com.proyecto.listaPendientes.infrastructure.entity.UsuarioEntity;
import com.proyecto.listaPendientes.infrastructure.mapper.UsuarioMapper;
import com.proyecto.listaPendientes.infrastructure.repository.RolRepository;
import com.proyecto.listaPendientes.infrastructure.repository.UsuarioRepository;
import com.proyecto.listaPendientes.infrastructure.rest.client.ClienteReniec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthenticationAdapterTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JWTServiceOut jwtServiceOut;
    @Mock
    private RolRepository rolRepository;
    @Mock
    private UsuarioMapper usuarioMapper;
    @Mock
    private ClienteReniec reniec;

    @InjectMocks
    private AuthenticationAdapter authenticationAdapter;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testSignUpUser() {
        SignUpRequest signUpRequest = new SignUpRequest("78451252","juan@gmail.com","juan123","DELEGADO");
        ResponseReniec responseReniec = getResponseReniec();
        ReflectionTestUtils.setField(authenticationAdapter, "tokenApi","XXXXXXXXXXXXX", String.class);

        when(reniec.getInfoReniec(anyString(),anyString())).thenReturn(responseReniec);

        assertDoesNotThrow(()-> authenticationAdapter.signUpUser(signUpRequest));
    }

    private ResponseReniec getResponseReniec(){
        return new ResponseReniec("Juan","Perez","Sanchez","78451252");
    }

}