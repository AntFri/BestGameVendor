package com.antoniofrische.bestgamevendor.services;

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
    boolean publisherSave(PublisherEntity publisher);
    boolean publisherDelet(Long iD);
    boolean publisherEdit(PublisherEntity publisher);

}
