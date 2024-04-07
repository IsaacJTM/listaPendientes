package com.proyecto.listaPendientes.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class RequestTarea {
    private String titulo;
    private String descripcion;
    private Date fechaCreacion;
    private String fechaVencimiento;
    private String usuario;
    private String categoria;
    private String comentario;

}
