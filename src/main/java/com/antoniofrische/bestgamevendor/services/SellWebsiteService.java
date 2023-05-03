package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.SellingWebsiteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SellWebsiteService {
    List<SellingWebsiteEntity> salesWebAll();
    Page<SellingWebsiteEntity> salesWebFindAllPage(Pageable pageable);
    Page<SellingWebsiteEntity> salesWebFindAllPageSearch(Pageable pageable, String search);
    SellingWebsiteEntity salesWebFindByID(Long id);
    List<SellingWebsiteEntity> salesWebFindByLimit(int offset);
    void salesWebSave(SellingWebsiteEntity salesWeb) throws EntityAlreadyExists, FormFieldEmpty;
    void salesWebDelet(Long id) throws EntityNotFound;
    void salesWebEdit(SellingWebsiteEntity salesWeb) throws EntityNotFound, FormFieldEmpty;
}
