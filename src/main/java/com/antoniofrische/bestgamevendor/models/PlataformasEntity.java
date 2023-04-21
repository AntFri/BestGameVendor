package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "plataformas", schema = "gamevendor", catalog = "")
public class PlataformasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPlataformas", nullable = false)
    private int idPlataformas;
    @Basic
    @Column(name = "Nombre", nullable = true, length = 45)
    private String nombre;
    @Basic
    @Column(name = "FechaSalida", nullable = true)
    private LocalDate fechaSalida;
    @Basic
    @Column(name = "Descripcion", nullable = true, length = 45)
    private String descripcion;

    public PlataformasEntity(int idPlataformas, String nombre, LocalDate fechaSalida, String descripcion, Set<ProductosEntity> prodlist) {
        this.idPlataformas = idPlataformas;
        this.nombre = nombre;
        this.fechaSalida = fechaSalida;
        this.descripcion = descripcion;

    }

    public PlataformasEntity() {
    }

    public int getIdPlataformas() {
        return idPlataformas;
    }

    public void setIdPlataformas(int idPlataformas) {
        this.idPlataformas = idPlataformas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlataformasEntity that = (PlataformasEntity) o;
        return idPlataformas == that.idPlataformas && Objects.equals(nombre, that.nombre) && Objects.equals(fechaSalida, that.fechaSalida) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPlataformas, nombre, fechaSalida, descripcion);
    }

    @Override
    public String toString() {
        return "PlataformasEntity{" +
                "idPlataformas=" + idPlataformas +
                ", nombre='" + nombre + '\'' +
                ", fechaSalida=" + fechaSalida +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
