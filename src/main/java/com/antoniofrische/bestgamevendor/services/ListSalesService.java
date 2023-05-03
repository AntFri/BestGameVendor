package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.ListaRebajasproductosEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.PublisherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ListSalesService {
    List<ListaRebajasproductosEntity> salesAll();
    Page<ListaRebajasproductosEntity> salesFindAllPage(Pageable pageable);
    Page<ListaRebajasproductosEntity> salesFindAllPageSearch(Pageable pageable, String search);
    ListaRebajasproductosEntity salesFindByID(Long iD);
    List<ListaRebajasproductosEntity> salesFindByLimit(int offset);
    List<ListaRebajasproductosEntity> salesFindByProduct(ProductosEntity product);
    void salesSave(ListaRebajasproductosEntity sales) throws EntityAlreadyExists, FormFieldEmpty;
    void salesDelet(Long iD) throws EntityNotFound;
    void salesEdit(ListaRebajasproductosEntity sales) throws EntityNotFound, FormFieldEmpty;
}
