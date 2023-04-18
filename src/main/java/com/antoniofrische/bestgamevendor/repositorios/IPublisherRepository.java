package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPublisherRepository extends JpaRepository<PublisherEntity, Long> {
    PublisherEntity findByNombreEqualsIgnoreCase(String nombre);
}
