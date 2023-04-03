package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.models.RegionEntity;

import java.util.List;

public interface RegionService {
    List<RegionEntity> regionFindAll();

    RegionEntity regionFindByID(Long id);
}
