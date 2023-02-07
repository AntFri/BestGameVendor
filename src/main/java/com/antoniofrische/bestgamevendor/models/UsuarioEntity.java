package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "gamevendor", catalog = "")
public class UsuarioEntity {
    @Id
    @Column(name = "idUsuario", nullable = false)
    private int idUsuario;
    @Basic
    @Column(name = "Nombre", nullable = true, length = 45)
    private String nombre;
    @Basic
    @Column(name = "Apellido", nullable = true, length = 45)
    private String apellido;
    @Basic
    @Column(name = "Email", nullable = true, length = 45)
    private String email;
    @Basic
    @Column(name = "FechaNacimiento", nullable = true)
    private Date fechaNacimiento;
    @Basic
    @Column(name = "Password", nullable = true, length = 45)
    private String password;
    @Basic
    @Column(name = "fk_region", nullable = false)
    private int fkRegion;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPassword() {

        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getFkRegion() {
        return fkRegion;
    }

    public void setFkRegion(int fkRegion) {
        this.fkRegion = fkRegion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return idUsuario == that.idUsuario && fkRegion == that.fkRegion && Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido) && Objects.equals(email, that.email) && Objects.equals(fechaNacimiento, that.fechaNacimiento) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombre, apellido, email, fechaNacimiento, password, fkRegion);
    }
}
