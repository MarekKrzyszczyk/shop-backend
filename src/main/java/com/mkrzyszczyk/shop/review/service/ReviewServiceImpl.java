package com.mkrzyszczyk.shop.review.service;

import com.mkrzyszczyk.shop.review.model.Review;
import com.mkrzyszczyk.shop.review.model.dto.ReviewDto;
import com.mkrzyszczyk.shop.review.repository.ReviewRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;

  @Override
  public List<Review> getReviews() {
    return reviewRepository.findAll();
  }

  @Override
  public Review addReview(ReviewDto reviewDto) {
    return reviewRepository.save(mapToEntity(reviewDto));
  }

  private Review mapToEntity(ReviewDto reviewDto) {
    return Review.builder()
        .authorName(HtmlParserUtils.clearHtmlContent(reviewDto.authorName()))
        .content(HtmlParserUtils.clearHtmlContent(reviewDto.content()))
        .productId(reviewDto.productId())
        .build();
  }
}
