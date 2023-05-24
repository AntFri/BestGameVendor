package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.ProductImageEntity;
import com.antoniofrische.bestgamevendor.repositorios.IProductImageRepository;
import com.antoniofrische.bestgamevendor.services.ProductImageService;
import com.antoniofrische.bestgamevendor.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.FormatFlagsConversionMismatchException;
import java.util.List;
import java.util.Objects;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private IProductImageRepository prodImgRepo;
    @Value("#{${imgFolder}}")
    private final Path root = null;

    @Override
    public void changePath() {

    }

    @Override
    public ProductImageEntity imageFindByName(String name) {
        return null;
    }

    @Override
    public List<ProductImageEntity> imageFindAll() {
        return prodImgRepo.findAll();
    }

    @Override
    public void save(MultipartFile file, String name) throws FormFieldEmpty, RuntimeException{
        ProductImageEntity prodImg = new ProductImageEntity();
        prodImg.setName(name);
        System.out.println(file.getOriginalFilename());
        try {

            Files.copy(file.getInputStream(),(Path) root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("after copy");
            String pathToImg = "/img"+"/"+file.getOriginalFilename();
            System.out.println("after path add");
            prodImg.setImgURL(pathToImg);
        } catch (IOException e) {
            throw new FormFieldEmpty(e.getMessage());
        }
        if(prodImg.getName().isEmpty() || prodImg.getImgURL().isEmpty()){
            throw new FormFieldEmpty("You need to fill all field out!");
        }
        prodImgRepo.save(prodImg);
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<String> loadAll(String filename) {
        return null;
    }
}
