package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.repositorios.IProductRepository;
import com.antoniofrische.bestgamevendor.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
