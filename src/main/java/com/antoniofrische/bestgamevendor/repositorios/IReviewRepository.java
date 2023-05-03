package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.ReviewEntity;
import com.antoniofrische.bestgamevendor.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReviewRepository extends JpaRepository<ReviewEntity, Long> {
    @Query("SELECT r FROM ReviewEntity r WHERE r.product = ?1")
    List<ReviewEntity> findByProduct(ProductosEntity product);

    List<ReviewEntity> findAllByUserIs(UserEntity user);

    boolean existsByReviewTextIsIgnoreCase(String reviewText);

    @Query("SELECT r FROM ReviewEntity r WHERE r.product.nombre LIKE %:searchTerm% OR r.user.nombre LIKE %:searchTerm% OR r.user.email LIKE %:searchTerm%")
    List<ReviewEntity> searchReview(@Param("searchTerm") String searchTerm);
}
