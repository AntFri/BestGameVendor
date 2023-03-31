package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.ListaFavoritosEntity;
import com.antoniofrische.bestgamevendor.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IListaFavoritos extends JpaRepository<ListaFavoritosEntity, Long> {

    @Query("SELECT lfe FROM ListaFavoritosEntity lfe WHERE lfe.user = ?1")
    public ListaFavoritosEntity findNameByUser(UserEntity user);


}
