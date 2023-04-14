package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
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
    void regionSave(RegionEntity region) throws EntityAlreadyExists, FormFieldEmpty;
    void regionDelet(Long iD) throws EntityNotFound;
    void regionEdit(RegionEntity region) throws EntityNotFound, FormFieldEmpty;
}
