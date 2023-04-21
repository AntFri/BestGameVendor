package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductImageRepository extends JpaRepository<ProductImageEntity, Long> {
}
