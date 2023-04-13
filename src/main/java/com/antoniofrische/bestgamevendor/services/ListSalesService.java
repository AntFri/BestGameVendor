package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.models.ListaRebajasproductosEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.PublisherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ListSalesService {
    List<ListaRebajasproductosEntity> salesAll();
    Page<ListaRebajasproductosEntity> salesFindAllPage(Pageable pageable);
    ListaRebajasproductosEntity salesFindByID(Long iD);
    List<ListaRebajasproductosEntity> salesFindByLimit(int offset);
    boolean salesSave(ListaRebajasproductosEntity sales);
    boolean salesDelet(Long iD);
    boolean salesEdit(ListaRebajasproductosEntity sales);
}
