package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.PublisherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublisherService {
    List<PublisherEntity> publisherAll();
    Page<PublisherEntity> publisherFindAllPage(Pageable pageable);
    PublisherEntity publisherFindByID(Long iD);
    List<PublisherEntity> publisherFindByLimit(int offset);
    void publisherSave(PublisherEntity publisher) throws EntityAlreadyExists, FormFieldEmpty;
    void publisherDelet(Long iD) throws EntityNotFound;
    void publisherEdit(PublisherEntity publisher) throws EntityNotFound, FormFieldEmpty;

}
