package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "lista_rebajasproductos", schema = "gamevendor", catalog = "")
public class ListaRebajasproductosEntity implements Comparable<ListaRebajasproductosEntity> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idListaRebajas", nullable = false)
    private int idListaRebajas;
    @Basic
    @Column(name = "Fecha_Cambio", nullable = true, length = 45)
    private LocalDate fechaCambio;
    @Basic
    @Column(name = "PrecioRebajas", nullable = true, precision = 0)
    private Double precioRebajas;
    @Basic
    @Column(name = "PercRebajas", nullable = true)
    private int percentageRebajas;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private SellingWebsiteEntity cellingwebsite;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private ProductosEntity productos;

    public int getIdListaRebajas() {
        return idListaRebajas;
    }

    public void setIdListaRebajas(int idListaPreciosBajos) {
        this.idListaRebajas = idListaPreciosBajos;
    }

    public LocalDate getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(LocalDate fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public Double getPrecioRebajas() {
        return precioRebajas;
    }

    public void setPrecioRebajas(Double precioRebajas) {
        this.precioRebajas = precioRebajas;
    }

    public int getPercentageRebajas() {
        return percentageRebajas;
    }

    public void setPercentageRebajas(int percentageRebajas) {
        this.percentageRebajas = percentageRebajas;
    }

    public SellingWebsiteEntity getCellingwebsite() {
        return cellingwebsite;
    }

    public void setCellingwebsite(SellingWebsiteEntity fkWebsite) {
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

    @Override
    public int compareTo(ListaRebajasproductosEntity o) {
        return precioRebajas.compareTo(o.getPrecioRebajas());
    }
}
