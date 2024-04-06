package com.proyecto.listaPendientes.infrastructure.adapter;

import com.proyecto.listaPendientes.domain.aggregates.constants.Constants;
import com.proyecto.listaPendientes.domain.aggregates.dto.UsuarioDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.SignInRequest;
import com.proyecto.listaPendientes.domain.aggregates.request.SignUpRequest;
import com.proyecto.listaPendientes.domain.aggregates.response.AuthenticationResponse;
import com.proyecto.listaPendientes.domain.aggregates.response.ResponseBase;
import com.proyecto.listaPendientes.domain.aggregates.response.ResponseReniec;
import com.proyecto.listaPendientes.domain.port.out.AuthenticationServiceOut;
import com.proyecto.listaPendientes.domain.port.out.JWTServiceOut;
import com.proyecto.listaPendientes.infrastructure.entity.RolEntity;
import com.proyecto.listaPendientes.infrastructure.entity.UsuarioEntity;
import com.proyecto.listaPendientes.infrastructure.mapper.UsuarioMapper;
import com.proyecto.listaPendientes.infrastructure.repository.RolRepository;
import com.proyecto.listaPendientes.infrastructure.repository.UsuarioRepository;
import com.proyecto.listaPendientes.infrastructure.rest.client.ClienteReniec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthenticationAdapter implements AuthenticationServiceOut {
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTServiceOut jwtServiceOut;
    private final RolRepository rolRepository;
    private final UsuarioMapper usuarioMapper;
    private final ClienteReniec reniec;

    @Value("${token.api}")
    private String tokenApi;
    @Override
    public UsuarioDTO signUpUser(SignUpRequest signUpRequest) {
        ResponseReniec responseReniec = getExecutionReniec(signUpRequest.getNumeroDocumento());
        UsuarioEntity user = getEntity(responseReniec,signUpRequest);
        user.setRol(rolRepository.findByNombreRol(signUpRequest.getRole()));
        usuarioRepository.save(user);
        return  usuarioMapper.mapToDTO(user);
    }

    @Override
    public UsuarioDTO signUpAdmin(SignUpRequest signUpRequest) {
        ResponseReniec responseReniec = getExecutionReniec(signUpRequest.getNumeroDocumento());
            UsuarioEntity admin = getEntity(responseReniec,signUpRequest);
            admin.setRol(rolRepository.findByNombreRol("ADMIN"));
            usuarioRepository.save(admin);
            return  usuarioMapper.mapToDTO(admin);
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        var user = usuarioRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email no valido"));

        var jwt = jwtServiceOut.generarTokenOut(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwt);
        return authenticationResponse;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }

    public ResponseReniec getExecutionReniec(String numero){
        String authorization = "Bearer "+tokenApi;
        ResponseReniec responseReniec = reniec.getInfoReniec(numero,authorization);
        return  responseReniec;
    }
    private UsuarioEntity getEntity(ResponseReniec reniec, SignUpRequest signUpRequest){
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNumeroDocumento(reniec.getNumeroDocumento());
        entity.setNombreUsuario(reniec.getNombres());
        entity.setApellidosUsuario(reniec.getApellidoPaterno()+" "+reniec.getApellidoMaterno());
        entity.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        entity.setEmail(signUpRequest.getEmail());
        entity.setEstadoUsuario(Constants.STATUS_ACTIVE);
        entity.setUserCreate(Constants.AUDIT_ADMIN);
        entity.setUserDateCreate(getTimestamp());
        return entity;
    }


}
