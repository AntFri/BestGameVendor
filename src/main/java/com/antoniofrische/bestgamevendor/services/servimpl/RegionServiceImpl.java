package com.antoniofrische.bestgamevendor.services.servimpl;

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
    public RegionEntity regionFindByID(Long id) {
        return regionRepo.findById(id).orElse(null);
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
    public List<RegionEntity> regionFindByLimit(int offset) {
        return null;
    }

    @Override
    public boolean regionSave(RegionEntity region) {
        return false;
    }

    @Override
    public boolean regionDelet(Long ID) {
        return false;
    }

    @Override
    public boolean regionEdit(RegionEntity region) {
        return false;
    }
}
