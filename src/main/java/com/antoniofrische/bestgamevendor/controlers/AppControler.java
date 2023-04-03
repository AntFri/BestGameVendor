package com.antoniofrische.bestgamevendor.controlers;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.RegionEntity;
import com.antoniofrische.bestgamevendor.models.UserEntity;

import com.antoniofrische.bestgamevendor.repositorios.IRegionRepository;
import com.antoniofrische.bestgamevendor.services.ProductService;
import com.antoniofrische.bestgamevendor.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app")
public class AppControler {
    Logger logger = LoggerFactory.getLogger(AppControler.class);
    @Autowired
    private ProductService prodServ;
    @Autowired
    private UserService userServ;

    @Autowired
    private IRegionRepository regionRepo;


    @GetMapping("/all_product")
    public List<ProductosEntity> getProductos() {
        return prodServ.prodAll();
    }

    @GetMapping("/all_product/{offset}")
    public List<ProductosEntity> getProductLimit(@PathVariable("offset") Integer offset){
        logger.info("Sending list of products size: " + offset);
        return prodServ.prodFindByLimit(offset);
    }

    @GetMapping(value = "/product/{id}")
    public Optional<ProductosEntity> getFraseById(@PathVariable("id") Long id) {
        logger.info("Sending Product with id: "+ id);
        return Optional.ofNullable(prodServ.prodFindByID(id));
    }

    @GetMapping("/all_region")
    public List<RegionEntity> getAllRegion(){
        logger.info("Sending region list");
        return regionRepo.findAll();
    }

    @GetMapping(value = "/user/{id}")
    public Optional<UserEntity> getUser(@PathVariable("id") Long id) {
        logger.info("Sending user with id: " + id);
        return Optional.ofNullable(userServ.userFindById(id));
    }
    @PostMapping("/add")
    public boolean addUser(@RequestBody UserEntity user) {
        UserEntity currentUser = userServ.userFindByEmail(user.getEmail());
        if(currentUser != null){
            return false;
        }
        logger.info("new User registering!" + user.getEmail());
        user.setRole("user");

        try {
            return userServ.userAdd(user);
        } catch (Exception e) {
            logger.error("New user could not be saved!" + user.getEmail());
            e.printStackTrace();
            return false;
        }
    }

}
