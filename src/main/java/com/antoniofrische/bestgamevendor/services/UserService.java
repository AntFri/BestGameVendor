package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.exceptions.UserAgeToLow;
import com.antoniofrische.bestgamevendor.exceptions.UserAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.UserNotFound;
import com.antoniofrische.bestgamevendor.models.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity userFindById(Long id);
    UserEntity userFindByEmail(String email);
    Boolean userAdd(UserEntity user);
    List<UserEntity> userFindAll();
    void processReg(UserEntity user) throws UserAlreadyExists, UserAgeToLow;
    boolean userDelete(Long userID) throws UserNotFound;
    boolean userEdit(UserEntity user, Long idU) throws UserNotFound, UserAlreadyExists;


}
