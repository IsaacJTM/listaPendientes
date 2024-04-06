package com.proyecto.listaPendientes.domain.port.out;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JWTServiceOut {

    String generarTokenOut(UserDetails userDetails);
    boolean validarTokenOut(String token, UserDetails userDetails);
    String extractUserNameOut(String token);

}
