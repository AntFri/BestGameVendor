package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductosEntity, Integer> {
}
