package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.models.CellingWebsiteEntity;
import com.antoniofrische.bestgamevendor.models.GenreEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CellWebsiteService {
    List<CellingWebsiteEntity> salesWebAll();
    Page<CellingWebsiteEntity> salesWebFindAllPage(Pageable pageable);
    CellingWebsiteEntity salesWebFindByID(Long id);
    List<CellingWebsiteEntity> salesWebFindByLimit(int offset);
    boolean salesWebSave(CellingWebsiteEntity salesWeb) throws EntityAlreadyExists;
    boolean salesWebDelet(Long prodID) throws EntityNotFound;
    boolean salesWebEdit(CellingWebsiteEntity salesWeb) throws EntityNotFound;
}
