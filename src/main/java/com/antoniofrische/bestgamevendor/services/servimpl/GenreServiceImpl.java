package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.models.GenreEntity;
import com.antoniofrische.bestgamevendor.models.ReviewEntity;
import com.antoniofrische.bestgamevendor.repositorios.IGenreRepository;
import com.antoniofrische.bestgamevendor.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private IGenreRepository gerneRepo;

    @Override
    public List<GenreEntity> genreAll() {
        return gerneRepo.findAll();
    }

    @Override
    public Page<GenreEntity> genreFindAllPage(Pageable pageable) {
        List<GenreEntity> genres = gerneRepo.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<GenreEntity> list;

        if (genres.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, genres.size());
            list = genres.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), genres.size());
    }

    @Override
    public GenreEntity genreFindByID(Long iD) {
        return gerneRepo.findById(iD).orElse(null);
    }

    @Override
    public List<GenreEntity> genreFindByLimit(int offset) {
        return null;
    }

    @Override
    public boolean genreSave(GenreEntity genre) {
        return false;
    }

    @Override
    public boolean genreDelet(Long ID) {
        return false;
    }

    @Override
    public boolean genreEdit(GenreEntity genre) {
        return false;
    }
}
