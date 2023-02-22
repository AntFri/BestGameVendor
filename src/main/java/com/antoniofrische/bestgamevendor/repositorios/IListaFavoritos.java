package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.ListaFavoritosEntity;
import com.antoniofrische.bestgamevendor.models.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IListaFavoritos extends JpaRepository<ListaFavoritosEntity, Long> {
    @Query("SELECT lfe FROM ListaFavoritosEntity lfe WHERE lfe.user = ?1")
    public List<ListaFavoritosEntity> findByUser(UsuarioEntity user);
}
