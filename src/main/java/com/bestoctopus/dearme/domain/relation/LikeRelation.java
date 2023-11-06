package com.bestoctopus.dearme.domain.relation;

import com.bestoctopus.dearme.domain.BookReview;
import com.bestoctopus.dearme.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "like_relation")
@Getter
@Builder
public class LikeRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_review_id",nullable = false)
    private BookReview bookReview;
}
