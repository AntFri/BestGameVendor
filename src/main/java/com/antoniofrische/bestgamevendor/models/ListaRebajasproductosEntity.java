package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "lista_rebajasproductos", schema = "gamevendor", catalog = "")
public class ListaRebajasproductosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idLista_preciosBajos", nullable = false)
    private int idListaPreciosBajos;
    @Basic
    @Column(name = "Fecha_Cambio", nullable = true, length = 45)
    private String fechaCambio;
    @Basic
    @Column(name = "PrecioRebajas", nullable = true, precision = 0)
    private Double precioRebajas;
    @Basic
    @Column(name = "fk_Website", nullable = false)
    private int fkWebsite;
    @Basic
    @Column(name = "fk_Product", nullable = false)
    private int fkProduct;

    public int getIdListaPreciosBajos() {
        return idListaPreciosBajos;
    }

    public void setIdListaPreciosBajos(int idListaPreciosBajos) {
        this.idListaPreciosBajos = idListaPreciosBajos;
    }

    public String getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(String fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public Double getPrecioRebajas() {
        return precioRebajas;
    }

    public void setPrecioRebajas(Double precioRebajas) {
        this.precioRebajas = precioRebajas;
    }

    public int getFkWebsite() {
        return fkWebsite;
    }

    public void setFkWebsite(int fkWebsite) {
        this.fkWebsite = fkWebsite;
    }

    public int getFkProduct() {
        return fkProduct;
    }

    public void setFkProduct(int fkProduct) {
        this.fkProduct = fkProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaRebajasproductosEntity that = (ListaRebajasproductosEntity) o;
        return idListaPreciosBajos == that.idListaPreciosBajos && fkWebsite == that.fkWebsite && fkProduct == that.fkProduct && Objects.equals(fechaCambio, that.fechaCambio) && Objects.equals(precioRebajas, that.precioRebajas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idListaPreciosBajos, fechaCambio, precioRebajas, fkWebsite, fkProduct);
    }
}
