package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.repositorios.IProductRepository;
import com.antoniofrische.bestgamevendor.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private IProductRepository prodRepo;
    @Override
    public boolean prodSave(ProductosEntity prod) {
        return false;
    }

    @Override
    public boolean prodDelet(Long prodID) {
        return false;
    }

    @Override
    public boolean prodEdit(ProductosEntity prod) {
        return false;
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
    public List<ProductosEntity> prodFindAllPage(Pageable pageable) {
        return prodRepo.findAllPage(pageable).getContent();
    }
}
