package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "plataformas_has_productos", schema = "gamevendor", catalog = "")
@IdClass(PlataformasHasProductosEntityPK.class)
public class PlataformasHasProductosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "fk_Plataformas", nullable = false)
    private int fkPlataformas;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "fk_Productos", nullable = false)
    private int fkProductos;

    public int getFkPlataformas() {
        return fkPlataformas;
    }

    public void setFkPlataformas(int fkPlataformas) {
        this.fkPlataformas = fkPlataformas;
    }

    public int getFkProductos() {
        return fkProductos;
    }

    public void setFkProductos(int fkProductos) {
        this.fkProductos = fkProductos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlataformasHasProductosEntity that = (PlataformasHasProductosEntity) o;
        return fkPlataformas == that.fkPlataformas && fkProductos == that.fkProductos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fkPlataformas, fkProductos);
    }
}
