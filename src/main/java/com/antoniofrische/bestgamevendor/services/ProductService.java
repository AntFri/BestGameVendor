package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;

import java.util.List;

public interface ProductService {
    public boolean saveProd(ProductosEntity prod);
    public boolean deletProd(Long prodID);
    public boolean editProd(ProductosEntity prod);
    public List<ProductosEntity> allProd();
    public ProductosEntity findByProdID(Long id);
    public List<ProductosEntity> findXProd(int offset);
}
