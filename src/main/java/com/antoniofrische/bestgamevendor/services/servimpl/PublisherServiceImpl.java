package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.PublisherEntity;
import com.antoniofrische.bestgamevendor.models.RegionEntity;
import com.antoniofrische.bestgamevendor.models.ReviewEntity;
import com.antoniofrische.bestgamevendor.repositorios.IPublisherRepository;
import com.antoniofrische.bestgamevendor.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private IPublisherRepository publisherRepo;

    @Override
    public List<PublisherEntity> publisherAll() {
        return publisherRepo.findAll();
    }

    @Override
    public Page<PublisherEntity> publisherFindAllPage(Pageable pageable) {
        List<PublisherEntity> publisher = publisherRepo.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<PublisherEntity> list;

        if (publisher.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, publisher.size());
            list = publisher.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), publisher.size());
    }

    @Override
    public PublisherEntity publisherFindByID(Long iD) {
        return publisherRepo.findById(iD).orElse(null);
    }

    @Override
    public List<PublisherEntity> publisherFindByLimit(int offset) {
        return null;
    }

    @Override
    public void publisherSave(PublisherEntity publisher) throws EntityAlreadyExists, FormFieldEmpty {
        if(publisher.getNombre().isEmpty() || publisher.getDescripcion().isEmpty() ||
                publisher.getFechaInauguracion() ==null || publisher.getOrigenContry() == null){
            throw new FormFieldEmpty("All field must be filled out!");
        }

        PublisherEntity publisherDB = publisherRepo.findByNombreEqualsIgnoreCase(publisher.getNombre());
        if(publisherDB != null){
            throw new EntityAlreadyExists("This Publisher alreday exists!");
        }
        publisherRepo.save(publisher);
    }

    @Override
    public void publisherDelet(Long iD) throws EntityNotFound{
        PublisherEntity publisherDB = publisherRepo.findById(iD).orElse(null);
        if(publisherDB == null){
            throw new EntityNotFound("This Publisher doesn't exsist!");
        }
        publisherRepo.delete(publisherDB);
    }

    @Override
    public void publisherEdit(PublisherEntity publisher) throws EntityNotFound, FormFieldEmpty{
        if(publisher.getNombre().isEmpty() || publisher.getDescripcion().isEmpty() ||
                publisher.getFechaInauguracion() ==null || publisher.getOrigenContry() == null){
            throw new FormFieldEmpty("All field must be filled out!");
        }
        if(!publisherRepo.existsById((long)publisher.getIdPublisher())){
            throw new EntityNotFound("This Publisher doesn't exist!");
        }
        publisherRepo.save(publisher);
    }
}
