package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.exceptions.UserAgeToLow;
import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.models.ListaFavoritosEntity;
import com.antoniofrische.bestgamevendor.models.ReviewEntity;
import com.antoniofrische.bestgamevendor.models.UserEntity;
import com.antoniofrische.bestgamevendor.repositorios.IListaFavoritos;
import com.antoniofrische.bestgamevendor.repositorios.IUserRepository;
import com.antoniofrische.bestgamevendor.services.ReviewService;
import com.antoniofrische.bestgamevendor.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private IUserRepository userRepo;
    @Autowired
    private ReviewService reviewServ;
    @Autowired
    private IListaFavoritos favRepo;

    @Override
    public UserEntity userFindById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public UserEntity userFindByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public Boolean userAdd(UserEntity user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("user");
        user.setAccountActive(true);
        userRepo.save(user);
        return true;
    }

    @Override
    public List<UserEntity> userFindAll() {
        return userRepo.findAll();
    }

    @Override
    public void processReg(UserEntity user) throws EntityAlreadyExists, UserAgeToLow, FormFieldEmpty {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        if(user.getNombre().isEmpty() || user.getApellido().isEmpty()||
                user.getPassword().isEmpty() || user.getEmail().isEmpty() ||
                user.getRegion() == null) {
            throw new FormFieldEmpty("All fields musst be filled out!");
        }

        UserEntity userDB = userRepo.findByEmail(user.getEmail());
        user.setPassword(encodedPassword);
        user.setRole("user");
        user.setAccountActive(true);

        if(userDB!=null)
            throw new EntityAlreadyExists("User allready exists!");
        int age = calcAge(user.getFechaNacimiento());
        if(age < 7){
            throw new UserAgeToLow("You must be older then 6 years");
        }
        userRepo.save(user);
    }

    @Override
    public void userDelete(Long userID) throws EntityNotFound {
        UserEntity user = userRepo.findById(userID).orElse(null);
        if(user == null){
            logger.error("User not exist!");
            throw  new EntityNotFound("Usuario no existe!");
        }
        ListaFavoritosEntity listaFav = favRepo.findNameByUser(user);
        if(listaFav != null){
            favRepo.delete(listaFav);
        }
        List<ReviewEntity> reviews = reviewServ.reviewFindAllByUser(user);
        if(reviews.size() > 0){
            reviewServ.reviewDeleteList(reviews);
        }
        userRepo.delete(user);
    }

    @Override
    public void userEdit(UserEntity user, Long idU) throws EntityNotFound, EntityAlreadyExists, FormFieldEmpty {
        if(user.getNombre().isEmpty() || user.getApellido().isEmpty()||
                user.getPassword().isEmpty() || user.getEmail().isEmpty() ||
                user.getRegion() == null) {
            throw new FormFieldEmpty("All fields musst be filled out!");
        }

        UserEntity userDB = userRepo.findById(idU).orElse(null);
        if(userDB == null){
            logger.error("User not exist!");
            throw new EntityNotFound("Usuario no Existe!");
        }
        if(!user.getEmail().equalsIgnoreCase(userDB.getEmail())){
            UserEntity emailUser = userRepo.findByEmail(user.getEmail());
            if(emailUser != null){
                throw  new EntityAlreadyExists("El email ya esta utilizado!");
            }
            logger.info("Email");
        }
        if(!user.getPassword().equals(userDB.getPassword())){
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            logger.info("Password");

        }
        userRepo.save(user);
    }

    @Override
    public Page<UserEntity> userFindAllPage(Pageable pageable) {
        List<UserEntity> users = userRepo.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<UserEntity> list;

        if (users.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, users.size());
            list = users.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), users.size());
    }

    private int calcAge(Date fechanac){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthdate = LocalDate.parse(fechanac.toString(),fmt);
        LocalDate now = LocalDate.now();
        return Period.between(birthdate,now).getYears();
    }

}
