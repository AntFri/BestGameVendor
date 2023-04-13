package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.models.PlataformasEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlatformService {
    List<PlataformasEntity> platformAll();
    Page<PlataformasEntity> platformFindAllPage(Pageable pageable);
    PlataformasEntity platformFindByID(Long iD);
    List<PlataformasEntity> platformFindByLimit(int offset);
    boolean platformSave(PlataformasEntity plat);
    boolean platformDelet(Long iD);
    boolean platformEdit(PlataformasEntity plat);

}
