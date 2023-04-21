package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "publisher", schema = "gamevendor", catalog = "")
public class PublisherEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPublisher", nullable = false)
    private int idPublisher;
    @Basic
    @Column(name = "nombre", nullable = true, length = 45)
    private String nombre;
    @Basic
    @Column(name = "fechaInauguracion", nullable = true)
    private LocalDate fechaInauguracion;
    @Basic
    @Column(name = "Descripcion", nullable = true, length = 45)
    private String descripcion;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private RegionEntity origenContry;


    public PublisherEntity(int idPublisher, String nombre, LocalDate fechaInauguracion, String descripcion, RegionEntity origenContry) {
        this.idPublisher = idPublisher;
        this.nombre = nombre;
        this.fechaInauguracion = fechaInauguracion;
        this.descripcion = descripcion;
        this.origenContry = origenContry;
    }

    public PublisherEntity() {
    }

    public int getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(int idPublisherDesenvolupador) {
        this.idPublisher = idPublisherDesenvolupador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInauguracion() {
        return fechaInauguracion;
    }

    public void setFechaInauguracion(LocalDate fechaInauguracion) {
        this.fechaInauguracion = fechaInauguracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public RegionEntity getOrigenContry() {
        return origenContry;
    }

    public void setOrigenContry(RegionEntity origenContry) {
        this.origenContry = origenContry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublisherEntity that = (PublisherEntity) o;
        return idPublisher == that.idPublisher && Objects.equals(nombre, that.nombre) && Objects.equals(fechaInauguracion, that.fechaInauguracion) && Objects.equals(descripcion, that.descripcion) && Objects.equals(origenContry, that.origenContry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPublisher, nombre, fechaInauguracion, descripcion, origenContry);
    }
}
