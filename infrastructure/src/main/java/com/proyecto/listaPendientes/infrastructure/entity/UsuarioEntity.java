package com.proyecto.listaPendientes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuario")
@RequiredArgsConstructor
@NamedQuery(name = "UsuarioEntity.findByIdUsuario", query = "SELECT u FROM UsuarioEntity u JOIN u.rol r WHERE u.idUsuario = :idUsuario AND r.idRol = 3")
public class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nombre_usuario", nullable = false, length = 45)
    private String nombreUsuario;

    @Column(name = "apellidos_usuario", nullable = false, length = 45)
    private String apellidosUsuario;

    @Column(name = "numero_documento", nullable = false, length = 8)
    private String numeroDocumento;

    @Column(name = "email_usuario", nullable = false, length = 65)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "telefono", length = 9)
    private String telefono;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "estado_usuario", nullable = false)
    private Integer estadoUsuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_rol_fk", nullable = false)
    private RolEntity rol;

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

    //USER DETAIL
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return List.of(new SimpleGrantedAuthority(rol.getNombreRol()));

    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
