package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.exceptions.UserAgeToLow;
import com.antoniofrische.bestgamevendor.exceptions.UserAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.UserNotFound;
import com.antoniofrische.bestgamevendor.models.UserEntity;

import java.util.List;

public interface UserService {
    public UserEntity userFindById(Long id);
    public UserEntity userFindByEmail(String email);
    public Boolean userAdd(UserEntity user);
    public List<UserEntity> findAllUsers();
    public void processReg(UserEntity user) throws UserAlreadyExists, UserAgeToLow;
    public boolean userDelete(Long userID) throws UserNotFound;
    public boolean userEdit(UserEntity user, Long idU) throws UserNotFound, UserAlreadyExists;


}
