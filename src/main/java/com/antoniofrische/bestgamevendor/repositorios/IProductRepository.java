package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductRepository extends JpaRepository<ProductosEntity, Long> {
    @Query(value="SELECT * FROM productos f LIMIT 10 OFFSET ?1", nativeQuery = true)
    List<ProductosEntity> getProductLimit(int offset);
}
