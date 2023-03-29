package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
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
    private Timestamp fechaSalida;
    @Basic
    @Column(name = "Descripcion", nullable = true, length = 45)
    private String descripcion;
    @ManyToMany(mappedBy = "platformList")
    private Set<ProductosEntity> prodlist;

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

    public Timestamp getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Timestamp fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<ProductosEntity> getProdlist() {
        return prodlist;
    }

    public void setProdlist(Set<ProductosEntity> prodlist) {
        this.prodlist = prodlist;
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
}
