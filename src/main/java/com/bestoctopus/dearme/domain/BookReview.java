package com.bestoctopus.dearme.domain;

import com.bestoctopus.dearme.domain.relation.BookReviewTagRelation;
import com.bestoctopus.dearme.domain.relation.LikeRelation;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "book_review")
@Getter
public class BookReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime lastEditTime;

    @OneToOne(mappedBy = "bookReview")
    private BookInform bookInform;

    @OneToMany(mappedBy = "bookReview")
    private Set<BookReviewTagRelation> tags;

    @OneToMany(mappedBy = "bookReview")
    private Set<LikeRelation> likes;
}
