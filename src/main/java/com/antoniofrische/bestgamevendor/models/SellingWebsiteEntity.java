package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "sellingwebsite", schema = "gamevendor", catalog = "")
public class SellingWebsiteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idWebsite", nullable = false)
    private int idsellingWebsite;
    @Basic
    @Column(name = "nombre", nullable = true, length = 45)
    private String nombre;
    @Basic
    @Column(name = "link", nullable = true, length = 45)
    private String link;

    public int getIdsellingWebsite() {
        return idsellingWebsite;
    }

    public void setIdsellingWebsite(int idcellingWebsite) {
        this.idsellingWebsite = idcellingWebsite;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellingWebsiteEntity that = (SellingWebsiteEntity) o;
        return idsellingWebsite == that.idsellingWebsite && Objects.equals(nombre, that.nombre) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idsellingWebsite, nombre, link);
    }
}
