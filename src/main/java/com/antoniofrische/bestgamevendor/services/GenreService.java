package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.models.GenreEntity;
import com.antoniofrische.bestgamevendor.models.ListaRebajasproductosEntity;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenreService {
    List<GenreEntity> genreAll();
    Page<GenreEntity> genreFindAllPage(Pageable pageable);
    GenreEntity genreFindByID(Long ID);
    List<GenreEntity> genreFindByLimit(int offset);
    boolean genreSave(GenreEntity genre);
    boolean genreDelet(Long ID);
    boolean genreEdit(GenreEntity genre);
}
