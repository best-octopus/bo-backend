package com.bestoctopus.dearme.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "book_inform")
@Getter
public class BookInform {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name="book_review_id")
    private BookReview bookReview;

    //이미지

    @Column(length=30, nullable = false)
    private String title;

    @Column(length=30)
    private String writer;

    @Column(length=30)
    private String publisher;
}
