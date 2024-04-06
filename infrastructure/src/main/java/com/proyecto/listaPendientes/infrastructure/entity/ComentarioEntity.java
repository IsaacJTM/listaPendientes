package com.proyecto.listaPendientes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "comentario")
@RequiredArgsConstructor
@NamedQuery(name = "ComentarioEntity.findByIdComentario", query = "select a from ComentarioEntity a where a.idComentario=:idComentario")
public class ComentarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long idComentario;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private Timestamp fechaActualizacion;

    @Column(name = "descripcion_comentario", nullable = false, length = 300)
    private String descripcionComentario;
}
