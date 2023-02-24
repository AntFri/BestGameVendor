package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.ListaFavoritosEntity;
import com.antoniofrische.bestgamevendor.models.ListaRebajasproductosEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IListaFavoritos extends JpaRepository<ListaFavoritosEntity, Long> {
    @Query("SELECT lfe.product FROM ListaFavoritosEntity lfe WHERE lfe.user = ?1")
    public List<ProductosEntity> findByUser(UsuarioEntity user);

    @Query("SELECT lfe FROM ListaFavoritosEntity lfe WHERE lfe.user = ?1 group by lfe.nombre")
    public ListaFavoritosEntity findNameByUser(UsuarioEntity user);

    @Query("SELECT lfe FROM ListaFavoritosEntity lfe WHERE lfe.product = ?1 and lfe.user=?2")
    public ListaFavoritosEntity findListByProd(ProductosEntity productos, UsuarioEntity user);

}
