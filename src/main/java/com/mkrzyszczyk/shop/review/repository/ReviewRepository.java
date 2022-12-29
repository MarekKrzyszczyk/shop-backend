package com.mkrzyszczyk.shop.review.repository;

import com.mkrzyszczyk.shop.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
