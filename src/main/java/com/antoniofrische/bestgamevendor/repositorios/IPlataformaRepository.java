package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.PlataformasEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlataformaRepository extends JpaRepository<PlataformasEntity, Long> {
}
