package com.mkrzyszczyk.shop.admin.review.controller;

import com.mkrzyszczyk.shop.admin.review.model.AdminReview;
import com.mkrzyszczyk.shop.admin.review.service.AdminReviewService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/reviews")
@RequiredArgsConstructor
public class AdminReviewController {

  private final AdminReviewService adminReviewService;

  @GetMapping
  public ResponseEntity<List<AdminReview>> getReviews(Pageable pageable) {
    return ResponseEntity.ok(adminReviewService.getReviews(pageable));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> acceptReview(@PathVariable Long id) {
    adminReviewService.acceptReview(id);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
    adminReviewService.deleteReview(id);
    return ResponseEntity.ok().build();
  }
}