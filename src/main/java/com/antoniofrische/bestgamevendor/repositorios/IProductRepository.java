package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<ProductosEntity, Long>, PagingAndSortingRepository<ProductosEntity,Long> {
    @Query(value="SELECT * FROM productos f LIMIT 10 OFFSET ?1", nativeQuery = true)
    List<ProductosEntity> findByProductLimit(int offset);

    @Query("SELECT p FROM ProductosEntity p WHERE p.nombre LIKE %:searchTerm% OR p.publisher.nombre LIKE %:searchTerm% OR p.genre.nombre LIKE %:searchTerm%")
    List<ProductosEntity> searchProducts(@Param("searchTerm") String searchTerm);

    ProductosEntity findByNombreEqualsIgnoreCase(String nombre);
}
