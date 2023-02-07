package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class PlataformasHasProductosEntityPK implements Serializable {
    @Column(name = "fk_Plataformas", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fkPlataformas;
    @Column(name = "fk_Productos", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        PlataformasHasProductosEntityPK that = (PlataformasHasProductosEntityPK) o;
        return fkPlataformas == that.fkPlataformas && fkProductos == that.fkProductos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fkPlataformas, fkProductos);
    }
}
