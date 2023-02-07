package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "productos", schema = "gamevendor", catalog = "")
public class ProductosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idProductos", nullable = false)
    private int idProductos;
    @Basic
    @Column(name = "nombre", nullable = true, length = 45)
    private String nombre;
    @Basic
    @Column(name = "edadMinima", nullable = false)
    private int edadMinima;
    @Basic
    @Column(name = "Photo_Producto", nullable = true, length = 200)
    private String photoProducto;
    @Basic
    @Column(name = "Descripcion", nullable = true, length = 400)
    private String descripcion;
    @Basic
    @Column(name = "FechaSalida", nullable = true)
    private Timestamp fechaSalida;
    @Basic
    @Column(name = "precioSalida", nullable = true, precision = 0)
    private Double precioSalida;
    @Basic
    @Column(name = "fk_Publisher", nullable = false)
    private int fkPublisher;
    @Basic
    @Column(name = "fk_Genre", nullable = false)
    private int fkGenre;
    @Basic
    @Column(name = "fk_Region", nullable = false)
    private int fkRegion;

    public int getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(int idProductos) {
        this.idProductos = idProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    public String getPhotoProducto() {
        return photoProducto;
    }

    public void setPhotoProducto(String photoProducto) {
        this.photoProducto = photoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Timestamp fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Double getPrecioSalida() {
        return precioSalida;
    }

    public void setPrecioSalida(Double precioSalida) {
        this.precioSalida = precioSalida;
    }

    public int getFkPublisher() {
        return fkPublisher;
    }

    public void setFkPublisher(int fkPublisher) {
        this.fkPublisher = fkPublisher;
    }

    public int getFkGenre() {
        return fkGenre;
    }

    public void setFkGenre(int fkGenre) {
        this.fkGenre = fkGenre;
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
        ProductosEntity that = (ProductosEntity) o;
        return idProductos == that.idProductos && edadMinima == that.edadMinima && fkPublisher == that.fkPublisher && fkGenre == that.fkGenre && fkRegion == that.fkRegion && Objects.equals(nombre, that.nombre) && Objects.equals(photoProducto, that.photoProducto) && Objects.equals(descripcion, that.descripcion) && Objects.equals(fechaSalida, that.fechaSalida) && Objects.equals(precioSalida, that.precioSalida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProductos, nombre, edadMinima, photoProducto, descripcion, fechaSalida, precioSalida, fkPublisher, fkGenre, fkRegion);
    }
}
