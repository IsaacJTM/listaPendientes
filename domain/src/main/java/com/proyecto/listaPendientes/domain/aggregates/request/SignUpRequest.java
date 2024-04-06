package com.proyecto.listaPendientes.domain.aggregates.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpRequest {
    private String numeroDocumento;
    private String email;
    private String password;
    private String role;
}
