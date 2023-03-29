package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

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
    @ManyToOne(cascade = CascadeType.REFRESH)
    private PublisherEntity publisher;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private GenreEntity genre;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private RegionEntity region;
    @ManyToMany(mappedBy = "productlist")
    private Set<ListaFavoritosEntity> listaFav;
    @ManyToMany
    @JoinTable(
            name = "prod_list_platform",
            joinColumns = @JoinColumn(name = "productos_idProductos"),
            inverseJoinColumns = @JoinColumn(name = "plataformas_idPlataformas"))
    private Set<PlataformasEntity> platformList;

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

    public PublisherEntity getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherEntity publisher) {
        this.publisher = publisher;
    }

    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity fkGenre) {
        this.genre = fkGenre;
    }

    public RegionEntity getRegion() {
        return region;
    }

    public void setRegion(RegionEntity fkRegion) {
        this.region = fkRegion;
    }

    public Set<ListaFavoritosEntity> getListaFav() {
        return listaFav;
    }

    public void setListaFav(Set<ListaFavoritosEntity> listaFav) {
        this.listaFav = listaFav;
    }

    public Set<PlataformasEntity> getPlatformList() {
        return platformList;
    }

    public void setPlatformList(Set<PlataformasEntity> platformList) {
        this.platformList = platformList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductosEntity that = (ProductosEntity) o;
        return idProductos == that.idProductos && edadMinima == that.edadMinima && publisher == that.publisher && genre == that.genre && region == that.region && Objects.equals(nombre, that.nombre) && Objects.equals(photoProducto, that.photoProducto) && Objects.equals(descripcion, that.descripcion) && Objects.equals(fechaSalida, that.fechaSalida) && Objects.equals(precioSalida, that.precioSalida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProductos, nombre, edadMinima, photoProducto, descripcion, fechaSalida, precioSalida, publisher, genre, region);
    }
}
