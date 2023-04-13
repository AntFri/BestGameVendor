package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
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
            throw new EntityAlreadyExists("The peoduct with that name allready exists!");
        }
        prodRepo.save(prod);
    }

    @Override
    public void prodDelet(Long prodID) {

    }

    @Override
    public void prodEdit(ProductosEntity prod) {

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
