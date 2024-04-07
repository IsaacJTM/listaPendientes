package com.proyecto.listaPendientes.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDTO {

    private Long idComentario;
    private Timestamp fechaCreacion;
    private Timestamp fechaActualizacion;
    private String descripcionComentario;
    private Integer estadoComentario;

    private String userCreate;
    private Timestamp userDateCreate;
    private String userUpdate;
    private Timestamp userDateUpdate;
    private String userDelete;
    private Timestamp userDateDelete;

    private String mensaje;
}
