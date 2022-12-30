package com.mkrzyszczyk.shop.review.controller;

import com.mkrzyszczyk.shop.common.model.Review;
import com.mkrzyszczyk.shop.review.dto.ReviewDto;
import com.mkrzyszczyk.shop.review.service.ReviewService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

  @GetMapping
  public ResponseEntity<List<Review>> getReviews() {
    return ResponseEntity.ok(reviewService.getReviews());
  }

  @PostMapping
  public ResponseEntity<Review> getReviews(@RequestBody ReviewDto reviewDto) {
    return new ResponseEntity<>(reviewService.addReview(reviewDto), HttpStatus.CREATED);
  }
}