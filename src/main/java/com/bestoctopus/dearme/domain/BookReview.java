package com.bestoctopus.dearme.domain;

import com.bestoctopus.dearme.domain.relation.BookReviewTagRelation;
import com.bestoctopus.dearme.domain.relation.LikeRelation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "book_review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookReview extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "book_data_id", nullable = false)
    private BookData bookData;

    @OneToMany(mappedBy = "bookReview", fetch = FetchType.LAZY)
    private final Set<BookReviewTagRelation> tags = new HashSet<>();

    @OneToMany(mappedBy = "bookReview", fetch = FetchType.LAZY)
    private final Set<LikeRelation> likes = new HashSet<>();

    @OneToMany(mappedBy="bookReview", fetch = FetchType.LAZY)
    private final List<BookComment> comments = new ArrayList<>();

    @Builder
    public BookReview(User user, String title, String content, BookData bookData) {
        this.user = user;
        this.title = title;
        this.content = content;
//        this.lastEditTime = lastEditTime;
        this.bookData = bookData;
    }

    public Set<String> getTagNameSet(){
        return getTags().stream().map(tagRel->tagRel.getTag().getName()).collect(Collectors.toSet());
    }
}