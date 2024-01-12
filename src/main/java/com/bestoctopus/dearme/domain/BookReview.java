package com.bestoctopus.dearme.domain;

import com.bestoctopus.dearme.domain.relation.BookReviewTagRelation;
import com.bestoctopus.dearme.domain.relation.LikeRelation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book_review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDate lastEditTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_data_id", nullable = false)
    private BookData bookData;

    @OneToMany(mappedBy = "bookReview")
    private final Set<BookReviewTagRelation> tags = new HashSet<>();

    @OneToMany(mappedBy = "bookReview")
    private final Set<LikeRelation> likes = new HashSet<>();

    @Builder
    public BookReview(Long id, User user, String title, String content, LocalDate lastEditTime, BookData bookData) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.lastEditTime = lastEditTime;
        this.bookData = bookData;
    }
}