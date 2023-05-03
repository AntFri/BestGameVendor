package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.PlataformasEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPlataformaRepository extends JpaRepository<PlataformasEntity, Long> {
    @Query("SELECT pl FROM PlataformasEntity pl WHERE pl.nombre LIKE %:searchTerm%")
    List<PlataformasEntity> searchPlatform(@Param("searchTerm") String searchTerm);
}
