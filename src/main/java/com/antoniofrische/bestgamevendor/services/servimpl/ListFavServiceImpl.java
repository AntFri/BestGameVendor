package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.models.ListaFavoritosEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.UserEntity;
import com.antoniofrische.bestgamevendor.repositorios.IListaFavoritos;
import com.antoniofrische.bestgamevendor.services.ListFavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListFavServiceImpl implements ListFavService {
    @Autowired
    private IListaFavoritos listFavRepo;

    @Override
    public ListaFavoritosEntity favFindByID(Long iD) {
        return listFavRepo.findById(iD).orElse(null);
    }

    @Override
    public ListaFavoritosEntity favFindByUsername(UserEntity user) {
        return listFavRepo.findNameByUser(user);
    }

    @Override
    public List<ListaFavoritosEntity> favFindAll() {
        return listFavRepo.findAll();
    }


    @Override
    public void favAddProd(ProductosEntity product, UserEntity user) throws EntityNotFound {
        ListaFavoritosEntity listFav = listFavRepo.findNameByUser(user);
        if(listFav == null){
            throw  new EntityNotFound("You don't have any favorite list!");
        }
        listFav.addProductToList(product);
        listFavRepo.save(listFav);
    }

    @Override
    public void favRemoveProd(ProductosEntity product, UserEntity user) throws EntityNotFound{
        ListaFavoritosEntity listFav = listFavRepo.findNameByUser(user);
        if(listFav == null){
            throw  new EntityNotFound("You don't have any favorite list!");
        }
        listFav.deleteProduct(product);
        listFavRepo.save(listFav);
    }

    @Override
    public void favAdd(ListaFavoritosEntity listfav, UserEntity user) throws EntityAlreadyExists {
        ListaFavoritosEntity listFavSearch = listFavRepo.findNameByUser(user);
        listfav.setUser(user);
        if(listFavSearch != null){
            throw  new EntityAlreadyExists("You already have a favorite list!!");
        }
        listFavRepo.save(listfav);
    }
}
