package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.ListaRebajasproductosEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IListaRebajasRepository extends JpaRepository<ListaRebajasproductosEntity, Long> {
    @Query("SELECT lrp FROM ListaRebajasproductosEntity lrp WHERE lrp.product = ?1")
    public ListaRebajasproductosEntity findByProductID(Long idProducto);
}
