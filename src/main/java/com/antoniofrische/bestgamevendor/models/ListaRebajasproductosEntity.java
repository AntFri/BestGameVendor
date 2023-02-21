package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "lista_rebajasproductos", schema = "gamevendor", catalog = "")
public class ListaRebajasproductosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idListaRebajas", nullable = false)
    private int idListaPreciosBajos;
    @Basic
    @Column(name = "Fecha_Cambio", nullable = true, length = 45)
    private String fechaCambio;
    @Basic
    @Column(name = "PrecioRebajas", nullable = true, precision = 0)
    private Double precioRebajas;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private CellingWebsiteEntity website;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private ProductosEntity product;

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

    public CellingWebsiteEntity getWebsite() {
        return website;
    }

    public void setWebsite(CellingWebsiteEntity fkWebsite) {
        this.website = fkWebsite;
    }

    public ProductosEntity getProduct() {
        return product;
    }

    public void setProduct(ProductosEntity fkProduct) {
        this.product = fkProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaRebajasproductosEntity that = (ListaRebajasproductosEntity) o;
        return idListaPreciosBajos == that.idListaPreciosBajos && website == that.website && product == that.product && Objects.equals(fechaCambio, that.fechaCambio) && Objects.equals(precioRebajas, that.precioRebajas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idListaPreciosBajos, fechaCambio, precioRebajas, website, product);
    }
}
