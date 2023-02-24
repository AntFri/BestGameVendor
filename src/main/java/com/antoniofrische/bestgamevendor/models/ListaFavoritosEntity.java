package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "lista_favoritos", schema = "gamevendor", catalog = "")
public class ListaFavoritosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCarrito_favoritos", nullable = false)
    private int idFavoritos;
    @Basic
    @Column(name = "nombre", nullable = true, length = 45)
    private String nombre;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "Usuario_idUsuario")
    private UsuarioEntity user;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "productos_idProductos")
    private ProductosEntity product;

    public int getIdFavoritos() {
        return idFavoritos;
    }

    public void setIdFavoritos(int idCarritoFavoritos) {
        this.idFavoritos = idCarritoFavoritos;
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
        return idFavoritos == that.idFavoritos && user == that.user && product == that.product && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFavoritos, nombre, user, product);
    }
}
