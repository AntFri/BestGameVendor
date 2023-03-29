package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.repositorios.IProductRepository;
import com.antoniofrische.bestgamevendor.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private IProductRepository prodRepo;
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
        return prodRepo.findAll();
    }

    @Override
    public ProductosEntity findByProdID(Long id) {
        return prodRepo.findById(id).orElse(null);
    }

    @Override
    public List<ProductosEntity> findByProdLimit(int offset) {
        return prodRepo.findByProductLimit(offset);
    }
}
