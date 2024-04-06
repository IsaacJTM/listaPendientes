package com.proyecto.listaPendientes.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComentarioDTO {

    private Long idComentario;
    private Timestamp fechaCreacion;
    private Timestamp fechaActualizacion;
    private String descripcionComentario;


    private String mensaje;
}
