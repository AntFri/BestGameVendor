package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.GenreEntity;
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
    private IGenreRepository genreRepo;

    @Override
    public List<GenreEntity> genreAll() {
        return genreRepo.findAll();
    }

    @Override
    public Page<GenreEntity> genreFindAllPage(Pageable pageable) {
        List<GenreEntity> genres = genreRepo.findAll();
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
        return genreRepo.findById(iD).orElse(null);
    }

    @Override
    public List<GenreEntity> genreFindByLimit(int offset) {
        return null;
    }

    @Override
    public void genreSave(GenreEntity genre) throws EntityAlreadyExists, FormFieldEmpty {
        if(genre.getNombre().isEmpty() || genre.getDescripcion().isEmpty()){
            throw new FormFieldEmpty("All fields must be filled out!");
        }

        if(genreRepo.existsById((long)genre.getIdGenre())){
            throw new EntityAlreadyExists("This Genre allready exists!");
        }
        genreRepo.save(genre);
    }

    @Override
    public void genreDelet(Long ID) throws EntityNotFound{
        GenreEntity genreDB = genreRepo.findById(ID).orElse(null);
        if(genreDB == null){
            throw new EntityNotFound("This Genre allready exists!");
        }
        genreRepo.delete(genreDB);
    }

    @Override
    public void genreEdit(GenreEntity genre) throws EntityNotFound, FormFieldEmpty{
        if(genre.getNombre().isEmpty() || genre.getDescripcion().isEmpty()){
            throw new FormFieldEmpty("All fields must be filled out!");
        }

        if(!genreRepo.existsById((long)genre.getIdGenre())){
            throw new EntityNotFound("This Genre allready exists!");
        }
        genreRepo.save(genre);
    }
}
