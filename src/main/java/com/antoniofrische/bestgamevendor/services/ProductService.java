package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    boolean prodSave(ProductosEntity prod);
    boolean prodDelet(Long prodID);
    boolean prodEdit(ProductosEntity prod);
    List<ProductosEntity> prodAll();
    ProductosEntity prodFindByID(Long id);
    List<ProductosEntity> prodFindByLimit(int offset);
    List<ProductosEntity> searchByKey(String key);

    Page<ProductosEntity> prodFindAllPage(Pageable pageable);
}
