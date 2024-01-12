package com.bestoctopus.dearme.domain.relation;

import com.bestoctopus.dearme.domain.BookReview;
import com.bestoctopus.dearme.domain.Tag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.print.Book;

@Entity
@Table(name = "book_review_tag_relation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookReviewTagRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_review_id", nullable = false)
    private BookReview bookReview;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;
}
