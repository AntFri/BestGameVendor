package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    private UserEntity user;
    @ManyToMany
    @JoinTable(
            name = "prod_listafav",
            joinColumns = @JoinColumn(name = "lista_favoritos_idCarrito_favoritos"),
            inverseJoinColumns = @JoinColumn(name = "productos_idProductos"))
    private Set<ProductosEntity> productlist;

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity fkUser) {
        this.user = fkUser;
    }

    public Set <ProductosEntity> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<ProductosEntity> producto) {
        this.productlist = new HashSet<>(producto);
    }

    public void addProductToList(ProductosEntity producto){
        this.productlist.add(producto);
    }

    public void deleteProduct(ProductosEntity producto){
        this.productlist.remove(producto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaFavoritosEntity that = (ListaFavoritosEntity) o;
        return idFavoritos == that.idFavoritos && user == that.user && productlist == that.productlist && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFavoritos, nombre, user, productlist);
    }
}
