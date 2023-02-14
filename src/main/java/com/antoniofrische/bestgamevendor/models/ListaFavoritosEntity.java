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
    @ManyToOne(cascade = CascadeType.REFRESH)
    @Column(name = "fk_User", nullable = false)
    private UsuarioEntity user;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @Column(name = "fk_Product", nullable = false)
    private ProductosEntity product;

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

    public UsuarioEntity getUser() {
        return user;
    }

    public void setUser(UsuarioEntity fkUser) {
        this.user = fkUser;
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
        ListaFavoritosEntity that = (ListaFavoritosEntity) o;
        return idCarritoFavoritos == that.idCarritoFavoritos && user == that.user && product == that.product && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCarritoFavoritos, nombre, user, product);
    }
}
