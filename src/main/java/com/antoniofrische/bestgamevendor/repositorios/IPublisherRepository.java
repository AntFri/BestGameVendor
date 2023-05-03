package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPublisherRepository extends JpaRepository<PublisherEntity, Long> {
    PublisherEntity findByNombreEqualsIgnoreCase(String nombre);

    @Query("SELECT pb FROM PublisherEntity pb WHERE pb.nombre LIKE %:searchTerm% OR pb.origenContry.nombre LIKE %:searchTerm%")
    List<PublisherEntity> searchPublisher(@Param("searchTerm") String searchTerm);
}
