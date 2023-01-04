package com.mkrzyszczyk.shop.admin.review.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminReview {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long productId;
  private String authorName;
  private String content;
  private boolean moderated;

  public void setModerated(boolean moderated) {
    this.moderated = moderated;
  }
}
