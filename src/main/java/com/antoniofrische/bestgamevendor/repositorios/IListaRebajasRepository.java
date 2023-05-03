package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.ListaRebajasproductosEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IListaRebajasRepository extends JpaRepository<ListaRebajasproductosEntity, Long> {
    @Query("SELECT lrp FROM ListaRebajasproductosEntity lrp WHERE lrp.productos = ?1")
    public List<ListaRebajasproductosEntity> findByProductID(ProductosEntity idProducto);

    @Query("SELECT lrp FROM ListaRebajasproductosEntity lrp WHERE lrp.productos.nombre LIKE %:searchTerm% OR lrp.cellingwebsite.nombre LIKE %:searchTerm%")
    List<ListaRebajasproductosEntity> searchSales(@Param("searchTerm") String searchTerm);

}
