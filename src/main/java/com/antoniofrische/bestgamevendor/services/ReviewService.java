package com.antoniofrische.bestgamevendor.services;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.ReviewEntity;
import com.antoniofrische.bestgamevendor.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    List<ReviewEntity> reviewFindAll();
    Page<ReviewEntity> reviewFindAllPage(Pageable pageable);
    ReviewEntity reviewFindByID(Long iD);
    List<ReviewEntity> reviewFindAllByUser(UserEntity useer);
    List<ReviewEntity> reviewFindByProduct(ProductosEntity product);
    void reviewDeleteList(List<ReviewEntity> reviews);
    boolean reviewSave(ReviewEntity review, UserEntity user);
    void reviewDelete(ReviewEntity review);
    boolean reviewEdit(ReviewEntity review);
}
