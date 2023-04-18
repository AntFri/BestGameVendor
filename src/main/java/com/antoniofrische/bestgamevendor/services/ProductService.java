package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    void prodSave(ProductosEntity prod) throws FormFieldEmpty, EntityAlreadyExists;
    void prodDelet(Long prodID) throws EntityNotFound;
    void prodEdit(ProductosEntity prod) throws FormFieldEmpty, EntityNotFound;
    List<ProductosEntity> prodAll();
    ProductosEntity prodFindByID(Long id);
    ProductosEntity prodFindByNombre(ProductosEntity prod);
    List<ProductosEntity> prodFindByLimit(int offset);
    List<ProductosEntity> searchByKey(String key);
    Page<ProductosEntity> prodFindAllPage(Pageable pageable);
}
