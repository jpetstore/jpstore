package org.csu.petstoremanage.service;

import org.csu.petstoremanage.domain.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    public Review getReview(int id);

    public void deleteReview(int id);

    public void insertReview(Review review);

    public List<Review> getAllReview();

    public List<Review> getItemReview(String itemid);

    public int setTop(int id);

    public void cancelTop(int id);
}
