package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "genre", schema = "gamevendor", catalog = "")
public class GenreEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idGenero(genre)", nullable = false)
    private int idGeneroGenre;
    @Basic
    @Column(name = "nombre", nullable = true, length = 45)
    private String nombre;
    @Basic
    @Column(name = "Description", nullable = true, length = 45)
    private String description;

    public int getIdGeneroGenre() {
        return idGeneroGenre;
    }

    public void setIdGeneroGenre(int idGeneroGenre) {
        this.idGeneroGenre = idGeneroGenre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreEntity that = (GenreEntity) o;
        return idGeneroGenre == that.idGeneroGenre && Objects.equals(nombre, that.nombre) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGeneroGenre, nombre, description);
    }
}
