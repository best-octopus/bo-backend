package com.bestoctopus.dearme.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book_data")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookData {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long isbn;

    @OneToMany(mappedBy = "bookData", fetch = FetchType.LAZY)
//    @JsonIgnore
    private final Set<BookReview> bookReviews = new HashSet<>();

    @Column(length=30, nullable = false)
    private String title;

    @Column(nullable = false)
    private String imgUrl;

    @Column(length=30)
    private String author;

    @Column(length=30)
    private String publisher;

    @Column(nullable = false)
    private String description;

    @Builder
    public BookData(Long isbn, String title, String imgUrl, String author, String publisher, String description){
        this.isbn = isbn;
        this.title = title;
        this.imgUrl = imgUrl;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
    }
}
