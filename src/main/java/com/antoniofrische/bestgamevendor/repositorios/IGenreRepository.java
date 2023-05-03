package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.GenreEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IGenreRepository extends JpaRepository<GenreEntity, Long> {
    @Query("SELECT gn FROM GenreEntity gn WHERE gn.nombre LIKE %:searchTerm%")
    List<GenreEntity> searchGenre(@Param("searchTerm") String searchTerm);
}
