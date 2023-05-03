package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.PlataformasEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlatformService {
    List<PlataformasEntity> platformAll();
    Page<PlataformasEntity> platformFindAllPage(Pageable pageable);
    Page<PlataformasEntity> platformFindAllPageSearch(Pageable pageable, String search);
    PlataformasEntity platformFindByID(Long iD);
    List<PlataformasEntity> platformFindByLimit(int offset);
    void platformSave(PlataformasEntity plat)throws EntityAlreadyExists, FormFieldEmpty;
    void platformDelet(Long iD) throws EntityNotFound;
    void platformEdit(PlataformasEntity plat)throws EntityNotFound, FormFieldEmpty;

}
