package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.exceptions.UserAgeToLow;
import com.antoniofrische.bestgamevendor.exceptions.UserAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.UserNotFound;
import com.antoniofrische.bestgamevendor.models.UserEntity;
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
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private IUserRepository userRepo;

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
    public void processReg(UserEntity user) throws UserAlreadyExists, UserAgeToLow {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("user");
        user.setAccountActive(true);
        UserEntity serUser = userRepo.findByEmail(user.getEmail());

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
    public boolean userDelete(Long userID) throws UserNotFound {
        UserEntity user = userRepo.findById(userID).orElse(null);
        if(user == null){
            logger.error("User not exist!");
            throw  new UserNotFound("Usuario no existe!");
        }
        userRepo.delete(user);
        return true;
    }

    @Override
    public boolean userEdit(UserEntity user, Long idU) throws UserNotFound, UserAlreadyExists{
        UserEntity userDB = userRepo.findById(idU).orElse(null);
        if(userDB == null){
            logger.error("User not exist!");
            throw new UserNotFound("Usuario no Existe!");
        }
        if(user.getNombre().length() > 0){
            userDB.setNombre(user.getNombre());
        }
        if(user.getApellido().length() > 0){
            logger.info("apellido");
            userDB.setApellido(user.getApellido());
        }
        if(!user.getEmail().equals(userDB.getEmail())){
            UserEntity emailUser = userRepo.findByEmail(user.getEmail());
            if(emailUser != null){
                throw  new UserAlreadyExists("El email ya esta utilizado!");
            }
            logger.info("Email");
            userDB.setEmail(user.getEmail());
        }
        if(user.getPassword().length() > 0){
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            logger.info("Password");
            userDB.setPassword(user.getPassword());
        }
        if(user.getRegion() != null){
            userDB.setRegion(user.getRegion());
        }
        if(user.getRole().length() > 0){
            userDB.setRole(user.getRole());
        }
        userDB.setAccountActive(user.getAccountActive());

        userRepo.save(user);
        return true;
    }

    private int calcAge(Date fechanac){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthdate = LocalDate.parse(fechanac.toString(),fmt);
        LocalDate now = LocalDate.now();
        return Period.between(birthdate,now).getYears();
    }

}
