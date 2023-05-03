package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.RegionEntity;
import com.antoniofrische.bestgamevendor.models.ReviewEntity;
import com.antoniofrische.bestgamevendor.repositorios.IRegionRepository;
import com.antoniofrische.bestgamevendor.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private IRegionRepository regionRepo;


    @Override
    public List<RegionEntity> regionFindAll() {
        return regionRepo.findAll();
    }

    @Override
    public RegionEntity regionFindByID(Long iD) {
        return regionRepo.findById(iD).orElse(null);
    }

    @Override
    public Page<RegionEntity> regionFindAllPage(Pageable pageable) {
        List<RegionEntity> regions = regionRepo.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<RegionEntity> list;

        if (regions.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, regions.size());
            list = regions.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), regions.size());
    }

    @Override
    public Page<RegionEntity> regionFindAllPageSearch(Pageable pageable, String search) {
        List<RegionEntity> regions;
        if(search != null){
            regions = regionRepo.searchRegion(search);
        }else {
            regions = regionRepo.findAll();
        }
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<RegionEntity> list;

        if (regions.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, regions.size());
            list = regions.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), regions.size());
    }

    @Override
    public List<RegionEntity> regionFindByLimit(int offset) {
        return null;
    }

    @Override
    public void regionSave(RegionEntity region) throws EntityAlreadyExists, FormFieldEmpty {
        if(region.getNombre().isEmpty() || region.getDescripcion().isEmpty()){
            throw new FormFieldEmpty("All field must be filled out!");
        }
        if(regionRepo.existsById((long)region.getIdRegion())){
            throw new EntityAlreadyExists("THis region alreday exists!");
        }
        regionRepo.save(region);
    }

    @Override
    public void regionDelet(Long iD) throws EntityNotFound{
        RegionEntity regionDB = regionRepo.findById(iD).orElse(null);
        if(regionDB == null){
            throw new EntityNotFound("This Region doesn't exsist!");
        }
        regionRepo.delete(regionDB);
    }

    @Override
    public void regionEdit(RegionEntity region) throws EntityNotFound, FormFieldEmpty{
        if(region.getNombre().isEmpty() || region.getDescripcion().isEmpty()){
            throw new FormFieldEmpty("All field must be filled out!");
        }
        if(!regionRepo.existsById((long)region.getIdRegion())){
            throw new EntityNotFound("THis region alreday exists!");
        }
        regionRepo.save(region);
    }
}
