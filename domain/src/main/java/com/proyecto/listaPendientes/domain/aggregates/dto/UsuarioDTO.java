package com.proyecto.listaPendientes.domain.aggregates.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.sql.Timestamp;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class UsuarioDTO {

    private Long idUsuario;
    private String nombreUsuario;
    private String apellidosUsuario;
    private String email;
    private String telefono;
    private String edad;
    private Integer estadoUsuario;
    private RolDTO rol;
    private String userCreate;
    private Timestamp userDateCreate;
    private String userUpdate;
    private Timestamp userDateUpdate;
    private String userDelete;
    private Timestamp userDateDelet;
}
