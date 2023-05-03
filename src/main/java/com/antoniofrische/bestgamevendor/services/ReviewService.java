package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.ReviewEntity;
import com.antoniofrische.bestgamevendor.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    List<ReviewEntity> reviewFindAll();
    Page<ReviewEntity> reviewFindAllPage(Pageable pageable);
    Page<ReviewEntity> reviewFindAllPageSearch(Pageable pageable, String search);
    ReviewEntity reviewFindByID(Long iD);
    List<ReviewEntity> reviewFindAllByUser(UserEntity useer);
    List<ReviewEntity> reviewFindByProduct(ProductosEntity product);
    void reviewDeleteList(List<ReviewEntity> reviews);
    void reviewSave(ReviewEntity review, UserEntity user) throws EntityAlreadyExists, EntityNotFound, FormFieldEmpty;
    void reviewDelete(ReviewEntity review, UserEntity user) throws EntityNotFound;

    void reviewAdminDelete(Long id) throws EntityNotFound;

}
