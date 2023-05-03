package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.GenreEntity;
import com.antoniofrische.bestgamevendor.models.ListaRebajasproductosEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenreService {
    List<GenreEntity> genreAll();
    Page<GenreEntity> genreFindAllPage(Pageable pageable);
    Page<GenreEntity> genreFindAllPageSearch(Pageable pageable, String search);
    GenreEntity genreFindByID(Long iD);
    List<GenreEntity> genreFindByLimit(int offset);
    void genreSave(GenreEntity genre) throws EntityAlreadyExists, FormFieldEmpty;
    void genreDelet(Long iD) throws EntityNotFound;
    void genreEdit(GenreEntity genre) throws EntityNotFound, FormFieldEmpty;
}
