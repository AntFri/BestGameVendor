package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "productimage", schema = "gamevendor", catalog = "")
public class ProductImageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    private String name;

    @Basic
    @Column(name = "imgURL", nullable = true, length = 200)
    private String imgURL;

    public ProductImageEntity( String name, String imgURL) {
        this.name = name;
        this.imgURL = imgURL;
    }

    public ProductImageEntity() {
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductImageEntity that = (ProductImageEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
