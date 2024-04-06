package com.proyecto.listaPendientes.domain.aggregates.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUsuario {

    private String nombreUsuario;
    private String apellidosUsuario;
    private String email;
    private String password;
    private String telefono;
    private Integer edad;
}
