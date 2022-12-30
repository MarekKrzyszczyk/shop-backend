package com.mkrzyszczyk.shop.review.service;

import com.mkrzyszczyk.shop.common.model.Review;
import com.mkrzyszczyk.shop.review.dto.ReviewDto;
import java.util.List;

public interface ReviewService {

  List<Review> getReviews();

  Review addReview(ReviewDto reviewDto);
}
