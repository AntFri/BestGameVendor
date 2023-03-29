package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.controlers.PageControler;
import com.antoniofrische.bestgamevendor.exceptions.UserAgeToLow;
import com.antoniofrische.bestgamevendor.exceptions.UserAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.UserNotFound;
import com.antoniofrische.bestgamevendor.models.UsuarioEntity;
import com.antoniofrische.bestgamevendor.repositorios.IUserRepository;
import com.antoniofrische.bestgamevendor.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private IUserRepository userRepo;

    @Override
    public UsuarioEntity userFindById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public UsuarioEntity userFindByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public Boolean userAdd(UsuarioEntity user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("user");
        user.setAccountActive(true);
        userRepo.save(user);
        return true;
    }

    @Override
    public void processReg(UsuarioEntity user) throws UserAlreadyExists, UserAgeToLow {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("user");
        user.setAccountActive(true);
        UsuarioEntity serUser = userRepo.findByEmail(user.getEmail());

        if(serUser!=null)
            throw new UserAlreadyExists("User allready exists!");
        int age = calcAge(user.getFechaNacimiento());
        logger.info(age+ "");
        if(age < 7){
            throw new UserAgeToLow("You must be older then 6 years");
        }
        userRepo.save(user);
    }

    @Override
    public boolean userDelete(UsuarioEntity user) throws UserNotFound {
        return false;
    }

    @Override
    public boolean userEdit(UsuarioEntity user) throws UserNotFound {
        return false;
    }

    private int calcAge(Date fechanac){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthdate = LocalDate.parse(fechanac.toString(),fmt);
        LocalDate now = LocalDate.now();
        return Period.between(birthdate,now).getYears();
    }

}
