package com.proyecto.listaPendientes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "tarea")
public class TareaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarea")
    private Long idTarea;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descripcion_tarea")
    private String descripcion;

    @Column(name = "fecha_creacion", nullable = false)
    private Timestamp fechaCreacion;

    @Column(name = "fecha_vencimiento", nullable = false)
    private Timestamp fechaVencimiento;

    @Column(name = "estado_tarea", nullable = false)
    private Integer estadoTarea;

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

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_comentario_fk")
    private ComentarioEntity comentario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_categoria_fk", nullable = false)
    private CategoriaEntity categoria;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario_fk", nullable = false)
    private UsuarioEntity usuario;
}
