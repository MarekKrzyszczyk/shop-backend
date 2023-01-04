package com.mkrzyszczyk.shop.admin.review.service;

import com.mkrzyszczyk.shop.admin.review.model.AdminReview;
import com.mkrzyszczyk.shop.admin.review.repository.AdminReviewRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminReviewServiceImpl implements AdminReviewService {

  private final AdminReviewRepository adminReviewRepository;

  @Override
  public List<AdminReview> getReviews(Pageable pageable) {
    return adminReviewRepository.findAll();
  }

  @Override
  @Transactional
  public void acceptReview(Long reviewId) {
    adminReviewRepository.acceptReview(reviewId);
  }

  @Override
  public void deleteReview(Long reviewId) {
    adminReviewRepository.deleteById(reviewId);
  }
}
