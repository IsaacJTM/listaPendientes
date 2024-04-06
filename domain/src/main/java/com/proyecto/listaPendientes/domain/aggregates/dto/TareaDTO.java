package com.proyecto.listaPendientes.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TareaDTO {

    private Long idTarea;
    private String titulo;
    private String descripcion;
    private Timestamp fechaCreacion;
    private Timestamp fechaVencimiento;
    private Integer estadoTarea;
    private String userCreate;
    private Timestamp userDateCreate;
    private String userUpdate;
    private Timestamp userDateUpdate;
    private String userDelete;
    private String comentario;
    private CategoriaDTO categoria;
    private UsuarioDTO usuario;

    private String mensaje;
}
