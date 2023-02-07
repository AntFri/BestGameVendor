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
    private Double reviewRating;
    @Basic
    @Column(name = "fk_User", nullable = false)
    private int fkUser;
    @Basic
    @Column(name = "fk_Products", nullable = false)
    private int fkProducts;

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

    public Double getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(Double reviewRating) {
        this.reviewRating = reviewRating;
    }

    public int getFkUser() {
        return fkUser;
    }

    public void setFkUser(int fkUser) {
        this.fkUser = fkUser;
    }

    public int getFkProducts() {
        return fkProducts;
    }

    public void setFkProducts(int fkProducts) {
        this.fkProducts = fkProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewEntity that = (ReviewEntity) o;
        return idReview == that.idReview && fkUser == that.fkUser && fkProducts == that.fkProducts && Objects.equals(reviewText, that.reviewText) && Objects.equals(reviewRating, that.reviewRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReview, reviewText, reviewRating, fkUser, fkProducts);
    }
}
