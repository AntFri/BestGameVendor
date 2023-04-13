package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.models.CellingWebsiteEntity;
import com.antoniofrische.bestgamevendor.models.ReviewEntity;
import com.antoniofrische.bestgamevendor.repositorios.ICellingWebsiteRepository;
import com.antoniofrische.bestgamevendor.services.CellWebsiteService;
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
public class CellWebsiteServiceImpl implements CellWebsiteService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private ICellingWebsiteRepository cellingwebRepo;

    @Override
    public List<CellingWebsiteEntity> salesWebAll() {
        return cellingwebRepo.findAll();

    }

    @Override
    public Page<CellingWebsiteEntity> salesWebFindAllPage(Pageable pageable) {
        List<CellingWebsiteEntity> reviews = cellingwebRepo.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<CellingWebsiteEntity> list;

        if (reviews.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, reviews.size());
            list = reviews.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), reviews.size());
    }

    @Override
    public CellingWebsiteEntity salesWebFindByID(Long id) {
        return cellingwebRepo.findById(id).orElse(null);
    }

    @Override
    public List<CellingWebsiteEntity> salesWebFindByLimit(int offset) {
        return null;
    }

    @Override
    public boolean salesWebSave(CellingWebsiteEntity salesWeb) throws EntityAlreadyExists {
        CellingWebsiteEntity webSiteDB = cellingwebRepo.findByNombreEqualsIgnoreCase(salesWeb.getNombre());
        if(webSiteDB != null){
            logger.warn("Celling website already exists in database!");
            throw  new EntityAlreadyExists("The website allready exists!");
        }
        cellingwebRepo.save(salesWeb);
        return true;
    }

    @Override
    public boolean salesWebDelet(Long prodID) {
        return false;
    }

    @Override
    public boolean salesWebEdit(CellingWebsiteEntity salesWeb) {
        return false;
    }
}
