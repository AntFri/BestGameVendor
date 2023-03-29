package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.exceptions.UserAgeToLow;
import com.antoniofrische.bestgamevendor.exceptions.UserAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.UserNotFound;
import com.antoniofrische.bestgamevendor.models.UsuarioEntity;

public interface UserService {
    public UsuarioEntity userFindById(Long id);
    public UsuarioEntity userFindByEmail(String email);
    public Boolean userAdd(UsuarioEntity user);
    public void processReg(UsuarioEntity user) throws UserAlreadyExists, UserAgeToLow;
    public boolean userDelete(UsuarioEntity user) throws UserNotFound;
    public boolean userEdit(UsuarioEntity user) throws UserNotFound;


}
