package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.services.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public boolean saveProd(ProductosEntity prod) {
        return false;
    }

    @Override
    public boolean deletProd(Long prodID) {
        return false;
    }

    @Override
    public boolean editProd(ProductosEntity prod) {
        return false;
    }

    @Override
    public List<ProductosEntity> allProd() {
        return null;
    }

    @Override
    public ProductosEntity findByProdID(Long id) {
        return null;
    }

    @Override
    public List<ProductosEntity> findXProd(int offset) {
        return null;
    }
}
