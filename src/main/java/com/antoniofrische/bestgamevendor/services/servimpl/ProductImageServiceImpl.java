package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.ProductImageEntity;
import com.antoniofrische.bestgamevendor.repositorios.IProductImageRepository;
import com.antoniofrische.bestgamevendor.services.ProductImageService;
import com.antoniofrische.bestgamevendor.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.FormatFlagsConversionMismatchException;
import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private IProductImageRepository prodImgRepo;

    private final Path root = Paths.get("./static/img");

    @Override
    public void changePath() {

    }

    @Override
    public void save(MultipartFile file, String name) throws FormFieldEmpty{
        ProductImageEntity prodImg = new ProductImageEntity();
        prodImg.setName(name);
        System.out.println(file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            String pathToImg = root+"/"+file.getOriginalFilename();
            prodImg.setImgURL(pathToImg);
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            throw new RuntimeException(e.getMessage());
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
