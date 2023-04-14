package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.ListaFavoritosEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.UserEntity;
import com.antoniofrische.bestgamevendor.repositorios.IListaFavoritos;
import com.antoniofrische.bestgamevendor.repositorios.IProductRepository;
import com.antoniofrische.bestgamevendor.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private IProductRepository prodRepo;
    @Autowired
    private IListaFavoritos favRepo;

    @Override
    public void prodSave(ProductosEntity prod)throws FormFieldEmpty, EntityAlreadyExists {
        if(prod.getNombre().isEmpty() || prod.getDescripcion().isEmpty()||
                prod.getGenre() ==null || prod.getPublisher() == null ||
                prod.getRegion() == null || prod.getFechaSalida() == null ||
                prod.getPrecioSalida().isNaN() || prod.getPrecioSalida() < 1 ||
                prod.getEdadMinima() < 1 || prod.getPhotoProducto() == null ||
                prod.getPlatformList() == null) {
            throw new FormFieldEmpty("All fields musst be filled out!");
        }

        ProductosEntity prodDB = prodRepo.findByNombreEqualsIgnoreCase(prod.getNombre());
        if(prodDB != null){
            throw new EntityAlreadyExists("The product with that name allready exists!");
        }
        prodRepo.save(prod);
    }

    @Override
    public void prodDelet(Long prodID) throws EntityNotFound {
        ProductosEntity prodDB = prodRepo.findById(prodID).orElse(null);
        if(prodDB == null){
            throw  new EntityNotFound("Product doesn't exist!");
        }
        List<ListaFavoritosEntity> favList = favRepo.findAll();
        for (ListaFavoritosEntity entity: favList) {
            if(entity.getProductlist().contains(prodDB)){
                entity.deleteProduct(prodDB);
                favRepo.save(entity);
            }
        }
        prodRepo.delete(prodDB);
    }

    @Override
    public void prodEdit(ProductosEntity prod) throws FormFieldEmpty, EntityNotFound{
        if(prod.getNombre().isEmpty() || prod.getDescripcion().isEmpty()||
                prod.getGenre() ==null || prod.getPublisher() == null ||
                prod.getRegion() == null || prod.getFechaSalida() == null ||
                prod.getPrecioSalida() < 1 || prod.getEdadMinima() < 1 ||
                prod.getPhotoProducto() == null || prod.getPlatformList() == null) {
            System.out.println(Arrays.toString(prod.getPlatformList().toArray()));
            throw new FormFieldEmpty("All fields musst be filled out!");
        }


        if(!prodRepo.existsById((long)prod.getIdProductos())){
            throw new EntityNotFound("The entity that you want to edit doesn't exist!");
        }
        prodRepo.save(prod);

    }

    @Override
    public List<ProductosEntity> prodAll() {
        return prodRepo.findAll();
    }

    @Override
    public ProductosEntity prodFindByID(Long id) {
        return prodRepo.findById(id).orElse(null);
    }

    @Override
    public List<ProductosEntity> prodFindByLimit(int offset) {
        return prodRepo.findByProductLimit(offset);
    }

    @Override
    public List<ProductosEntity> searchByKey(String key) {
        if(key != null)
            return prodRepo.searchProducts(key);
        return null;
    }

    @Override
    public Page<ProductosEntity> prodFindAllPage(Pageable pageable) {
        List<ProductosEntity> products = prodRepo.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ProductosEntity> list;

        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), products.size());
    }
}
