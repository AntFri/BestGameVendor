package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;


import java.time.LocalDate;
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
    @ManyToOne(cascade = CascadeType.REFRESH)
    private ProductImageEntity photoProducto;
    @Basic
    @Column(name = "Descripcion", nullable = true, length = 400)
    private String descripcion;
    @Basic
    @Column(name = "FechaSalida", nullable = true)
    private LocalDate fechaSalida;
    @Basic
    @Column(name = "precioSalida", nullable = true, precision = 0)
    private Double precioSalida;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private PublisherEntity publisher;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private GenreEntity genre;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private RegionEntity region;
    @ManyToMany
    @JoinTable(
            name = "prod_list_platform",
            joinColumns = @JoinColumn(name = "productos_idProductos"),
            inverseJoinColumns = @JoinColumn(name = "plataformas_idPlataformas"))
    private Set<PlataformasEntity> platformList;

    public ProductosEntity(int idProductos, String nombre, int edadMinima, ProductImageEntity photoProducto, String descripcion, LocalDate fechaSalida, Double precioSalida, PublisherEntity publisher, GenreEntity genre, RegionEntity region, Set<ListaFavoritosEntity> listaFav, Set<PlataformasEntity> platformList) {
        this.idProductos = idProductos;
        this.nombre = nombre;
        this.edadMinima = edadMinima;
        this.photoProducto = photoProducto;
        this.descripcion = descripcion;
        this.fechaSalida = fechaSalida;
        this.precioSalida = precioSalida;
        this.publisher = publisher;
        this.genre = genre;
        this.region = region;
        this.platformList = platformList;
    }

    public ProductosEntity() {
    }

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

    public ProductImageEntity getPhotoProducto() {
        return photoProducto;
    }

    public void setPhotoProducto(ProductImageEntity photoProducto) {
        this.photoProducto = photoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
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

    @Override
    public String toString() {
        return "ProductosEntity{" +
                "idProductos=" + idProductos +
                ", nombre='" + nombre + '\'' +
                ", edadMinima=" + edadMinima +
                ", photoProducto='" + photoProducto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaSalida=" + fechaSalida +
                ", precioSalida=" + precioSalida +
                ", publisher=" + publisher +
                ", genre=" + genre +
                ", region=" + region +
                ", Platform="+platformList.toString()+
                '}';
    }
}
