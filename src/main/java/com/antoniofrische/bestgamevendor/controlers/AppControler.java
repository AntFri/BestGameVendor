package com.antoniofrische.bestgamevendor.controlers;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.exceptions.UserAgeToLow;
import com.antoniofrische.bestgamevendor.models.*;

import com.antoniofrische.bestgamevendor.repositorios.IRegionRepository;
import com.antoniofrische.bestgamevendor.services.*;
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
    private ReviewService reviewServ;
    @Autowired
    private RegionService regionServ;
    @Autowired
    private ListSalesService lsServ;

    @GetMapping("/productAll")
    public List<ProductosEntity> getProductos() {
        return prodServ.prodAll();
    }

    @GetMapping("/productAll/{offset}")
    public List<ProductosEntity> getProductLimit(@PathVariable("offset") Integer offset){
        logger.info("Sending list of products size: " + offset);
        return prodServ.prodFindByLimit(offset);
    }

    @GetMapping(value = "/product/{id}")
    public Optional<ProductosEntity> getFraseById(@PathVariable("id") Long id) {
        logger.info("Sending Product with id: "+ id);
        return Optional.ofNullable(prodServ.prodFindByID(id));
    }

    @GetMapping("/regionAll")
    public List<RegionEntity> getAllRegion(){
        logger.info("Sending region list");
        return regionServ.regionFindAll();
    }

    @GetMapping("/reviewProd/{id}")
    public List<ReviewEntity> getAllReview(@PathVariable("id") Long id){
        logger.info("Sending region list");
        return reviewServ.reviewFindAll();
    }

    @GetMapping("/salesProdList/{id}")
    public List<ListaRebajasproductosEntity> getSalesByProd(@PathVariable Long id){
        ProductosEntity prodDB = prodServ.prodFindByID(id);
        if(prodDB == null){
            return null;
        }
        return lsServ.salesFindByProduct(prodDB);
    }

    @GetMapping(value = "/user/{id}")
    public Optional<UserEntity> getUser(@PathVariable("id") Long id) {
        logger.info("Sending user with id: " + id);
        return Optional.ofNullable(userServ.userFindById(id));
    }
    @PostMapping("/user/signup")
    public boolean addUser(@RequestBody UserEntity user) {
        try {
            userServ.processReg(user);
            return true;
        }catch (EntityAlreadyExists | UserAgeToLow | FormFieldEmpty e) {
            logger.warn(e.getMessage());
            return false;
        }
    }

    @PostMapping("/user/signin")
    public boolean userSingnIn(@RequestBody UserEntity user) {
        UserEntity userDB = userServ.userFindByEmail(user.getEmail());
        if(userDB == null){
            return false;
        }
        return user.getPassword().equals(userDB.getPassword());
    }
}
