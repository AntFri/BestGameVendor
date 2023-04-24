package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.ProductImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService {
    void changePath();
    ProductImageEntity imageFindByName(String name);
    List<ProductImageEntity> imageFindAll();
    void save(MultipartFile file, String name) throws FormFieldEmpty;
    void delete(long id);
    void deleteAll();
    List<String> loadAll(String filename);


}
