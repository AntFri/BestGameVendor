package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRegionRepository extends JpaRepository<RegionEntity, Long> {
    @Query("SELECT rg FROM RegionEntity rg WHERE rg.nombre LIKE %:searchTerm%")
    List<RegionEntity> searchRegion(@Param("searchTerm") String searchTerm);
}
