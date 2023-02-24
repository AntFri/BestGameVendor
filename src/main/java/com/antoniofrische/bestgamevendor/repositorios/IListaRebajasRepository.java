package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.ListaRebajasproductosEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IListaRebajasRepository extends JpaRepository<ListaRebajasproductosEntity, Long> {
    @Query("SELECT lrp FROM ListaRebajasproductosEntity lrp WHERE lrp.productos = ?1")
    public List<ListaRebajasproductosEntity> findByProductID(ProductosEntity idProducto);


}
