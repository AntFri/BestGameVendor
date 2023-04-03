package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.models.RegionEntity;
import com.antoniofrische.bestgamevendor.repositorios.IRegionRepository;
import com.antoniofrische.bestgamevendor.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
