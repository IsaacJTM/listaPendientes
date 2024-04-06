package com.proyecto.listaPendientes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "rol")
@RequiredArgsConstructor
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_rol")
    private Long idRol;

    @Column(name ="nombre_Rol")
    private String nombreRol;

    @Column(name = "user_create", length = 45)
    private String userCreate;

    @Column(name = "user_date_create")
    private Timestamp userDateCreate;

    @Column(name = "user_update", length = 45)
    private String userUpdate;

    @Column(name = "user_date_update")
    private Timestamp userDateUpdate;

    @Column(name = "user_delete", length = 45)
    private String userDelete;

}
