package com.bestoctopus.dearme.domain.relation;

import com.bestoctopus.dearme.domain.BookReview;
import com.bestoctopus.dearme.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "like_relation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_review_id", nullable = false)
    private BookReview bookReview;

    @Builder
    public LikeRelation(User user, BookReview bookReview) {
        this.user = user;
        this.bookReview = bookReview;
    }
}
