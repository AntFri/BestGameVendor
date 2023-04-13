package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.models.PublisherEntity;
import com.antoniofrische.bestgamevendor.models.RegionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RegionService {
    List<RegionEntity> regionFindAll();
    RegionEntity regionFindByID(Long iD);
    Page<RegionEntity> regionFindAllPage(Pageable pageable);
    List<RegionEntity> regionFindByLimit(int offset);
    boolean regionSave(RegionEntity region);
    boolean regionDelet(Long iD);
    boolean regionEdit(RegionEntity region);
}
