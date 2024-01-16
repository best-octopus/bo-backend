package com.bestoctopus.dearme.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookComment extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "book_review_id", nullable = false)
    private BookReview bookReview;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
