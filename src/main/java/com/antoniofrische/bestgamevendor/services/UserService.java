package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.exceptions.UserAgeToLow;
import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserEntity userFindById(Long id);
    UserEntity userFindByEmail(String email);
    Boolean userAdd(UserEntity user);
    List<UserEntity> userFindAll();
    void processReg(UserEntity user) throws EntityAlreadyExists, UserAgeToLow, FormFieldEmpty;
    void userDelete(Long userID) throws EntityNotFound;
    void userEdit(UserEntity user, Long idU) throws EntityNotFound, EntityAlreadyExists, FormFieldEmpty;

    Page<UserEntity> userFindAllPage(Pageable pageable);
    Page<UserEntity> userFindAllPageSearch(Pageable pageable, String search);

}
