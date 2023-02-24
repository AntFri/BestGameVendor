package com.antoniofrische.bestgamevendor.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "review", schema = "gamevendor", catalog = "")
public class ReviewEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idReview", nullable = false)
    private int idReview;
    @Basic
    @Column(name = "Review_text", nullable = true, length = 500)
    private String reviewText;
    @Basic
    @Column(name = "Review_rating", nullable = true, precision = 0)
    private String reviewRating;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "Usuario_idUsuario")
    private UsuarioEntity user;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "productos_idProductos")
    private ProductosEntity product;

    public int getIdReview() {
        return idReview;
    }

    public void setIdReview(int idReview) {
        this.idReview = idReview;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(String reviewRating) {
        this.reviewRating = reviewRating;
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

    public void setProduct(ProductosEntity fkProducts) {
        this.product = fkProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewEntity that = (ReviewEntity) o;
        return idReview == that.idReview && user == that.user && product == that.product && Objects.equals(reviewText, that.reviewText) && Objects.equals(reviewRating, that.reviewRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReview, reviewText, reviewRating, user, product);
    }
}
