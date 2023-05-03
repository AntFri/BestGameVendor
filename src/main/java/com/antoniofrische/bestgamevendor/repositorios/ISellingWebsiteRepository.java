package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.SellingWebsiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISellingWebsiteRepository extends JpaRepository<SellingWebsiteEntity, Long> {
    SellingWebsiteEntity findByNombreEqualsIgnoreCase(String name);

    @Query("SELECT cw FROM SellingWebsiteEntity cw WHERE cw.nombre LIKE %:searchTerm%")
    List<SellingWebsiteEntity> searchSellingWebsite(@Param("searchTerm") String searchTerm);
}
