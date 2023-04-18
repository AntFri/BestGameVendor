package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "region", schema = "gamevendor", catalog = "")
public class RegionEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idRegion", nullable = false)
    private int idRegion;
    @Basic
    @Column(name = "nombre", nullable = true, length = 45)
    private String nombre;
    @Basic
    @Column(name = "desciption", nullable = true, length = -1)
    private String descripcion;

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String desciption) {
        this.descripcion = desciption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionEntity that = (RegionEntity) o;
        return idRegion == that.idRegion && Objects.equals(nombre, that.nombre) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRegion, nombre, descripcion);
    }
}
