package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.ListaRebajasproductosEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.ReviewEntity;
import com.antoniofrische.bestgamevendor.repositorios.IListaRebajasRepository;
import com.antoniofrische.bestgamevendor.services.ListSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ListSalesServiceImpl implements ListSalesService {
    @Autowired
    private IListaRebajasRepository listSaleRepo;


    @Override
    public List<ListaRebajasproductosEntity> salesAll() {
        return listSaleRepo.findAll();
    }

    @Override
    public Page<ListaRebajasproductosEntity> salesFindAllPage(Pageable pageable) {
        List<ListaRebajasproductosEntity> sales = listSaleRepo.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ListaRebajasproductosEntity> list;

        if (sales.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, sales.size());
            list = sales.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), sales.size());
    }

    @Override
    public Page<ListaRebajasproductosEntity> salesFindAllPageSearch(Pageable pageable, String search) {
        List<ListaRebajasproductosEntity> sales;
        if(search != null){
            sales = listSaleRepo.searchSales(search);
        }else {
            sales = listSaleRepo.findAll();
        }
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ListaRebajasproductosEntity> list;

        if (sales.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, sales.size());
            list = sales.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), sales.size());
    }

    @Override
    public ListaRebajasproductosEntity salesFindByID(Long iD) {
        return listSaleRepo.findById(iD).orElse(null);
    }

    @Override
    public List<ListaRebajasproductosEntity> salesFindByLimit(int offset) {
        return null;
    }

    @Override
    public List<ListaRebajasproductosEntity> salesFindByProduct(ProductosEntity product) {
        return listSaleRepo.findByProductID(product);
    }

    @Override
    public void salesSave(ListaRebajasproductosEntity sales) throws FormFieldEmpty {
        if(sales.getFechaCambio() == null || sales.getPrecioRebajas() < 0 ||
            sales.getCellingwebsite() == null || sales.getProductos() == null){
            throw new FormFieldEmpty("All fields must be filled out!");
        }
        sales.setPercentageRebajas((int) ((int)(sales.getPrecioRebajas()*100)/sales.getProductos().getPrecioSalida()));
        listSaleRepo.save(sales);
    }

    @Override
    public void salesDelet(Long iD) throws EntityNotFound{
        ListaRebajasproductosEntity salesDB = listSaleRepo.findById(iD).orElse(null);
        if(salesDB == null){
            throw new EntityNotFound("This Sales doesn't exsist, so it can't be eliminated!");
        }
        listSaleRepo.delete(salesDB);
    }

    @Override
    public void salesEdit(ListaRebajasproductosEntity sales) throws EntityNotFound, FormFieldEmpty{
        ListaRebajasproductosEntity salesDB = listSaleRepo.findById((long)sales.getIdListaRebajas()).orElse(null);
        if(salesDB == null){
            throw new EntityNotFound("The sales you want to edit doesn Exist!");
        }

        if(sales.getFechaCambio() == null || sales.getPrecioRebajas() < 0 ||
                sales.getCellingwebsite() == null || sales.getProductos() == null){
            throw new FormFieldEmpty("All fields must be filled out!");
        }
        sales.setPercentageRebajas(calcPerRed(sales));
        listSaleRepo.save(sales);
    }

    private int calcPerRed(ListaRebajasproductosEntity sales){

        int diference = (int) (sales.getProductos().getPrecioSalida() - sales.getPrecioRebajas());

        return (int) ((diference*100)/ sales.getProductos().getPrecioSalida());
    }
}
