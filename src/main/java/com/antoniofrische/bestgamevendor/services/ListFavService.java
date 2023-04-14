package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.models.ListaFavoritosEntity;
import com.antoniofrische.bestgamevendor.models.ListaRebajasproductosEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface ListFavService {
    ListaFavoritosEntity favFindByID(Long iD);
    ListaFavoritosEntity favFindByUsername(UserEntity user);
    List<ListaFavoritosEntity> favFindAll();
    void favAddProd(ProductosEntity product, UserEntity user) throws EntityAlreadyExists, EntityNotFound;
    void favRemoveProd(ProductosEntity product, UserEntity user) throws EntityNotFound;
    void favAdd(ListaFavoritosEntity listfav, UserEntity user) throws EntityAlreadyExists, EntityNotFound;
}
