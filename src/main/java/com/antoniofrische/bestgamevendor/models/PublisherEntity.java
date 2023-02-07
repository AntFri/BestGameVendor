package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "publisher", schema = "gamevendor", catalog = "")
public class PublisherEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPublisher(Desenvolupador)", nullable = false)
    private int idPublisherDesenvolupador;
    @Basic
    @Column(name = "nombre", nullable = true, length = 45)
    private String nombre;
    @Basic
    @Column(name = "fechaInauguracion", nullable = true)
    private Timestamp fechaInauguracion;
    @Basic
    @Column(name = "Descripcion", nullable = true, length = 45)
    private String descripcion;
    @Basic
    @Column(name = "Origen_Contry", nullable = true, length = 45)
    private String origenContry;

    public int getIdPublisherDesenvolupador() {
        return idPublisherDesenvolupador;
    }

    public void setIdPublisherDesenvolupador(int idPublisherDesenvolupador) {
        this.idPublisherDesenvolupador = idPublisherDesenvolupador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Timestamp getFechaInauguracion() {
        return fechaInauguracion;
    }

    public void setFechaInauguracion(Timestamp fechaInauguracion) {
        this.fechaInauguracion = fechaInauguracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getOrigenContry() {
        return origenContry;
    }

    public void setOrigenContry(String origenContry) {
        this.origenContry = origenContry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublisherEntity that = (PublisherEntity) o;
        return idPublisherDesenvolupador == that.idPublisherDesenvolupador && Objects.equals(nombre, that.nombre) && Objects.equals(fechaInauguracion, that.fechaInauguracion) && Objects.equals(descripcion, that.descripcion) && Objects.equals(origenContry, that.origenContry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPublisherDesenvolupador, nombre, fechaInauguracion, descripcion, origenContry);
    }
}
