package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.PlataformasEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.ReviewEntity;
import com.antoniofrische.bestgamevendor.repositorios.IPlataformaRepository;
import com.antoniofrische.bestgamevendor.services.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PlatformServiceImpl implements PlatformService {
    @Autowired
    private IPlataformaRepository platformRepo;

    @Override
    public List<PlataformasEntity> platformAll() {
        return platformRepo.findAll();
    }

    @Override
    public Page<PlataformasEntity> platformFindAllPage(Pageable pageable) {
        List<PlataformasEntity> platforms = platformRepo.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<PlataformasEntity> list;

        if (platforms.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, platforms.size());
            list = platforms.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), platforms.size());
    }

    @Override
    public Page<PlataformasEntity> platformFindAllPageSearch(Pageable pageable, String search) {
        List<PlataformasEntity> platforms;
        if(search != null){
            platforms = platformRepo.searchPlatform(search);
        }else {
            platforms  = platformRepo.findAll();
        }
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<PlataformasEntity> list;

        if (platforms.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, platforms.size());
            list = platforms.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), platforms.size());
    }

    @Override
    public PlataformasEntity platformFindByID(Long iD) {
        return platformRepo.findById(iD).orElse(null);
    }

    @Override
    public List<PlataformasEntity> platformFindByLimit(int offset) {
        return null;
    }

    @Override
    public void platformSave(PlataformasEntity plat) throws EntityAlreadyExists, FormFieldEmpty{
        if(plat.getNombre().isEmpty() || plat.getDescripcion().isEmpty() || plat.getFechaSalida() == null){
            throw  new FormFieldEmpty("All fields must be field out!");
        }

        if(platformRepo.existsById((long)plat.getIdPlataformas())){
           throw new EntityAlreadyExists("This platform already exists!");
        }
        platformRepo.save(plat);
    }

    @Override
    public void platformDelet(Long iD) throws EntityNotFound{
        PlataformasEntity platformDB = platformRepo.findById(iD).orElse(null);
        if(platformDB == null){
            throw new EntityNotFound("This platform doesn't exsist!");
        }
        platformRepo.delete(platformDB);
    }

    @Override
    public void platformEdit(PlataformasEntity plat) throws EntityNotFound, FormFieldEmpty{
        if(plat.getNombre().isEmpty() || plat.getDescripcion().isEmpty() || plat.getFechaSalida() == null){
            throw  new FormFieldEmpty("All fields must be field out!");
        }

        if(!platformRepo.existsById((long)plat.getIdPlataformas())){
            throw new EntityNotFound("This platform already exists!");
        }
        platformRepo.save(plat);
    }
}
