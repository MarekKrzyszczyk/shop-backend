package com.mkrzyszczyk.shop.review.dto;

import org.hibernate.validator.constraints.Length;

public record ReviewDto(@Length(max = 60) String authorName, @Length(max = 600) String content, Long productId) {

}
