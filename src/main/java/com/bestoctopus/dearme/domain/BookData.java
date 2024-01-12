package com.bestoctopus.dearme.domain;

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
    private Long isbn;

    @OneToMany(mappedBy = "bookData")
    private final Set<BookReview> bookReviews = new HashSet<>();

    @Column(length=30, nullable = false)
    private String title;

    private String imgUrl;

    @Column(length=30)
    private String author;

    @Column(length=30)
    private String publisher;

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
