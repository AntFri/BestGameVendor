package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRegionRepository extends JpaRepository<RegionEntity, Long> {
}
