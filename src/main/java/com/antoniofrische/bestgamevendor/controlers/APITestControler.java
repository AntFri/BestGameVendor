package com.antoniofrische.bestgamevendor.controlers;

import com.antoniofrische.bestgamevendor.models.*;
import com.antoniofrische.bestgamevendor.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APITestControler {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private IProductRepository iProductRepository;
    @Autowired
    private IPublisherRepository iPublisherRepository;
    @Autowired
    private IRegionRepository iRegionRepository;
    @Autowired
    private IGenreRepository iGenreRepository;
    @Autowired
    private IPlataformaRepository iPlataformaRepository;
    @Autowired
    private ICellingWebsiteRepository iCellingWebsiteRepository;
    @Autowired
    private IListaRebajasRepository iListaRebajasRepository;
    @Autowired
    private IReviewRepository iReviewRepository;


    @GetMapping("/user_All")
    public List<UserEntity> allUsers (){
        return iUserRepository.findAll();
    }
    @GetMapping("/product_All")
    public List<ProductosEntity> allProducts (){
        return iProductRepository.findAll();
    }
    @GetMapping("/publisher_All")
    public List<PublisherEntity> allPublisher (){
        return iPublisherRepository.findAll();
    }
    @GetMapping("/region_All")
    public List<RegionEntity> allRegion (){
        return iRegionRepository.findAll();
    }
    @GetMapping("/gerne_All")
    public List<GenreEntity> allGenre (){
        return iGenreRepository.findAll();
    }
    @GetMapping("/plataforma_All")
    public List<PlataformasEntity> allPlataforma (){
        return iPlataformaRepository.findAll();
    }
    @GetMapping("/cellingweb_All")
    public List<CellingWebsiteEntity> allCellingWeb (){
        return iCellingWebsiteRepository.findAll();
    }
    @GetMapping("/listrebaja_All")
    public List<ListaRebajasproductosEntity> allListarebajas (){
        return iListaRebajasRepository.findAll();
    }
    @GetMapping("/review_All")
    public List<ReviewEntity> allReview (){
        return iReviewRepository.findAll();
    }
}
