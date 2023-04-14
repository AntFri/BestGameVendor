package com.antoniofrische.bestgamevendor.services.servimpl;

import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.ReviewEntity;
import com.antoniofrische.bestgamevendor.models.UserEntity;
import com.antoniofrische.bestgamevendor.repositorios.IReviewRepository;
import com.antoniofrische.bestgamevendor.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private IReviewRepository reviewRepo;

    @Override
    public List<ReviewEntity> reviewFindAll() {
        return reviewRepo.findAll();
    }

    @Override
    public Page<ReviewEntity> reviewFindAllPage(Pageable pageable) {
        List<ReviewEntity> reviews = reviewRepo.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ReviewEntity> list;

        if (reviews.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, reviews.size());
            list = reviews.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), reviews.size());
    }

    @Override
    public ReviewEntity reviewFindByID(Long iD) {
        return reviewRepo.findById(iD).orElse(null);
    }

    @Override
    public List<ReviewEntity> reviewFindAllByUser(UserEntity useer) {
        return reviewRepo.findAllByUserIs(useer);
    }

    @Override
    public List<ReviewEntity> reviewFindByProduct(ProductosEntity product) {
        return reviewRepo.findByProduct(product);
    }

    @Override
    public void reviewDeleteList(List<ReviewEntity> reviews) {
        reviewRepo.deleteAll(reviews);
    }

    @Override
    public void reviewSave(ReviewEntity review, UserEntity user) throws EntityAlreadyExists, EntityNotFound, FormFieldEmpty {
        if(user == null){
            throw new EntityNotFound("You must be login to add a review!");
        }
        if(review.getReviewText().length() < 1 || Integer.parseInt(review.getReviewRating()) < 1){
            throw new FormFieldEmpty("All fields must be filled out to add a review!");
        }
        if(reviewRepo.existsByReviewTextIsIgnoreCase(review.getReviewText())){
            throw new EntityAlreadyExists("This review Text exists exactly, don't be a bot!");
        }
        review.setUser(user);
        reviewRepo.save(review);
    }

    @Override
    public void reviewDelete(ReviewEntity review, UserEntity user) throws EntityNotFound{
        if(review == null){
            throw new EntityNotFound("THe review that you want to delet doen't exist!");
        }
        if(user == null){
            throw new EntityNotFound("You must be login to delete a review!");
        }

        reviewRepo.delete(review);
    }

    @Override
    public void reviewAdminDelete(Long id) throws EntityNotFound {
        ReviewEntity reviewDB = reviewRepo.findById(id).orElse(null);
        if(reviewDB == null){
            throw new EntityNotFound("The review doesn't exsist and can't be eliminated!");
        }
        reviewRepo.delete(reviewDB);
    }


}
