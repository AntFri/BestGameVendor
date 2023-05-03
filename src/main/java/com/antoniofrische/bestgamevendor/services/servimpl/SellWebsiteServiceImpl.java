package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.SellingWebsiteEntity;
import com.antoniofrische.bestgamevendor.repositorios.ISellingWebsiteRepository;
import com.antoniofrische.bestgamevendor.services.SellWebsiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SellWebsiteServiceImpl implements SellWebsiteService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private ISellingWebsiteRepository sellingwebRepo;

    @Override
    public List<SellingWebsiteEntity> salesWebAll() {
        return sellingwebRepo.findAll();

    }

    @Override
    public Page<SellingWebsiteEntity> salesWebFindAllPage(Pageable pageable) {
        List<SellingWebsiteEntity> sellingWeb = sellingwebRepo.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<SellingWebsiteEntity> list;

        if (sellingWeb.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, sellingWeb.size());
            list = sellingWeb.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), sellingWeb.size());
    }

    @Override
    public Page<SellingWebsiteEntity> salesWebFindAllPageSearch(Pageable pageable, String search) {
        List<SellingWebsiteEntity> sellingWeb;
        if(search != null){
            sellingWeb = sellingwebRepo.searchSellingWebsite(search);
        } else {
            sellingWeb = sellingwebRepo.findAll();
        }
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<SellingWebsiteEntity> list;

        if (sellingWeb.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, sellingWeb.size());
            list = sellingWeb.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), sellingWeb.size());
    }

    @Override
    public SellingWebsiteEntity salesWebFindByID(Long id) {
        return sellingwebRepo.findById(id).orElse(null);
    }

    @Override
    public List<SellingWebsiteEntity> salesWebFindByLimit(int offset) {
        return null;
    }

    @Override
    public void salesWebSave(SellingWebsiteEntity salesWeb) throws EntityAlreadyExists, FormFieldEmpty {
        if(salesWeb.getNombre().isEmpty() || salesWeb.getLink().isEmpty()){
            throw  new FormFieldEmpty("All fields must be field out!");
        }

        SellingWebsiteEntity webSiteDB = sellingwebRepo.findByNombreEqualsIgnoreCase(salesWeb.getNombre());
        if(webSiteDB != null){
            logger.warn("Celling website already exists in database!");
            throw  new EntityAlreadyExists("The website allready exists!");
        }
        sellingwebRepo.save(salesWeb);
    }

    @Override
    public void salesWebDelet(Long id) throws EntityNotFound{
        SellingWebsiteEntity webSiteDB = sellingwebRepo.findById(id).orElse(null);
        if(webSiteDB == null){
            throw  new EntityNotFound("The website doesn't exists!");
        }
        sellingwebRepo.delete(webSiteDB);
    }

    @Override
    public void salesWebEdit(SellingWebsiteEntity salesWeb) throws EntityNotFound, FormFieldEmpty{
        if(salesWeb.getNombre().isEmpty() || salesWeb.getLink().isEmpty()){
            throw  new FormFieldEmpty("All fields must be field out!");
        }
        SellingWebsiteEntity webSiteDB = sellingwebRepo.findById((long)salesWeb.getIdcellingWebsite()).orElse(null);
        if(webSiteDB == null){
            throw  new EntityNotFound("The website doesn't exists!");
        }
        sellingwebRepo.save(salesWeb);
    }
}
