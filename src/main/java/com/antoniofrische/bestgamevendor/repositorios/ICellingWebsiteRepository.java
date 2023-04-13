package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.CellingWebsiteEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICellingWebsiteRepository extends JpaRepository<CellingWebsiteEntity, Long> {
    CellingWebsiteEntity findByNombreEqualsIgnoreCase(String name);
}
