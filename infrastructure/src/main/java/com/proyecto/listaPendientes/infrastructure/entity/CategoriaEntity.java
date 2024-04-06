package com.proyecto.listaPendientes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "categoria")
@RequiredArgsConstructor
@NamedQuery(name = "CategoriaEntity.findByIdCategoria", query = "select a from CategoriaEntity a where a.idCategoria=:idCategoria")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "nombre_categoria")
    private String nombreCategoria;

    @Column(name = "descripcion_categoria")
    private String descripcionCategoria;

    @Column(name = "estado_parentesco", nullable = false)
    private Integer estadoParentesco;

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

    @Column(name = "user_date_delet")
    private Timestamp userDateDelet;

    public CategoriaEntity(long l, String s) {
    }
}
