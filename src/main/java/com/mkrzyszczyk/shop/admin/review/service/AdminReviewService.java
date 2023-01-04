package com.mkrzyszczyk.shop.admin.review.service;

import com.mkrzyszczyk.shop.admin.review.model.AdminReview;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface AdminReviewService {

  List<AdminReview> getReviews(Pageable pageable);

  void acceptReview(Long reviewId);

  void deleteReview(Long reviewId);
}
