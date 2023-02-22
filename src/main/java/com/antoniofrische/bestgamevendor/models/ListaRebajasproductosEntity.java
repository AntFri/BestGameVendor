package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "lista_rebajasproductos", schema = "gamevendor", catalog = "")
public class ListaRebajasproductosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idListaRebajas", nullable = false)
    private int idListaRebajas;
    @Basic
    @Column(name = "Fecha_Cambio", nullable = true, length = 45)
    private String fechaCambio;
    @Basic
    @Column(name = "PrecioRebajas", nullable = true, precision = 0)
    private Double precioRebajas;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private CellingWebsiteEntity cellingwebsite;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private ProductosEntity productos;

    public int getIdListaRebajas() {
        return idListaRebajas;
    }

    public void setIdListaRebajas(int idListaPreciosBajos) {
        this.idListaRebajas = idListaPreciosBajos;
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

    public CellingWebsiteEntity getCellingwebsite() {
        return cellingwebsite;
    }

    public void setCellingwebsite(CellingWebsiteEntity fkWebsite) {
        this.cellingwebsite = fkWebsite;
    }

    public ProductosEntity getProductos() {
        return productos;
    }

    public void setProductos(ProductosEntity fkProduct) {
        this.productos = fkProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaRebajasproductosEntity that = (ListaRebajasproductosEntity) o;
        return idListaRebajas == that.idListaRebajas && cellingwebsite == that.cellingwebsite && productos == that.productos && Objects.equals(fechaCambio, that.fechaCambio) && Objects.equals(precioRebajas, that.precioRebajas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idListaRebajas, fechaCambio, precioRebajas, cellingwebsite, productos);
    }
}
