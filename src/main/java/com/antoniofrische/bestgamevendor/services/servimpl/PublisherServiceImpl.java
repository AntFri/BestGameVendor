package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.models.PublisherEntity;
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
        return null;
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
    public PublisherEntity publisherFindByID(Long ID) {
        return null;
    }

    @Override
    public List<PublisherEntity> publisherFindByLimit(int offset) {
        return null;
    }

    @Override
    public boolean publisherSave(PublisherEntity publisher) {
        return false;
    }

    @Override
    public boolean publisherDelet(Long ID) {
        return false;
    }

    @Override
    public boolean publisherEdit(PublisherEntity publisher) {
        return false;
    }
}
