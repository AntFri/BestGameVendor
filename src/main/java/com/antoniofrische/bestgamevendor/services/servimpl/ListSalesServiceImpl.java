package com.antoniofrische.bestgamevendor.services.servimpl;

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
        return null;
    }

    @Override
    public Page<ListaRebajasproductosEntity> salesFindAllPage(Pageable pageable) {
        List<ListaRebajasproductosEntity> reviews = listSaleRepo.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ListaRebajasproductosEntity> list;

        if (reviews.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, reviews.size());
            list = reviews.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), reviews.size());
    }

    @Override
    public ListaRebajasproductosEntity salesFindByID(Long ID) {
        return null;
    }

    @Override
    public List<ListaRebajasproductosEntity> salesFindByLimit(int offset) {
        return null;
    }

    @Override
    public boolean salesSave(ListaRebajasproductosEntity sales) {
        return false;
    }

    @Override
    public boolean salesDelet(Long ID) {
        return false;
    }

    @Override
    public boolean salesEdit(ListaRebajasproductosEntity sales) {
        return false;
    }
}
