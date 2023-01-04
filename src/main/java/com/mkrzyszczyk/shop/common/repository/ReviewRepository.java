package com.mkrzyszczyk.shop.common.repository;

import com.mkrzyszczyk.shop.common.model.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

  List<Review> findAllByProductIdAndModerated(Long productId, boolean moderated);
}
