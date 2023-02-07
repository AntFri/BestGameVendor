package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "lista_favoritos", schema = "gamevendor", catalog = "")
public class ListaFavoritosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCarrito_favoritos", nullable = false)
    private int idCarritoFavoritos;
    @Basic
    @Column(name = "nombre", nullable = true, length = 45)
    private String nombre;
    @Basic
    @Column(name = "fk_User", nullable = false)
    private int fkUser;
    @Basic
    @Column(name = "fk_Product", nullable = false)
    private int fkProduct;

    public int getIdCarritoFavoritos() {
        return idCarritoFavoritos;
    }

    public void setIdCarritoFavoritos(int idCarritoFavoritos) {
        this.idCarritoFavoritos = idCarritoFavoritos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFkUser() {
        return fkUser;
    }

    public void setFkUser(int fkUser) {
        this.fkUser = fkUser;
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
        ListaFavoritosEntity that = (ListaFavoritosEntity) o;
        return idCarritoFavoritos == that.idCarritoFavoritos && fkUser == that.fkUser && fkProduct == that.fkProduct && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCarritoFavoritos, nombre, fkUser, fkProduct);
    }
}
