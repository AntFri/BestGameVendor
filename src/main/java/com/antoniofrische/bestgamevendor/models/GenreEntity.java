package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "genre", schema = "gamevendor", catalog = "")
public class GenreEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idGenre", nullable = false)
    private int idGenre;
    @Basic
    @Column(name = "nombre", nullable = true, length = 45)
    private String nombre;
    @Basic
    @Column(name = "Description", nullable = true, length = 45)
    private String descripcion;

    public GenreEntity(int idGenre, String nombre, String descripcion) {
        this.idGenre = idGenre;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public GenreEntity() {
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGeneroGenre) {
        this.idGenre = idGeneroGenre;
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

    public void setDescripcion(String description) {
        this.descripcion = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreEntity that = (GenreEntity) o;
        return idGenre == that.idGenre && Objects.equals(nombre, that.nombre) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGenre, nombre, descripcion);
    }
}
