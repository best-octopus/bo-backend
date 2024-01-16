package com.bestoctopus.dearme.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class BookReviewListDto {
    private Long id;

    private String author;

    private String title;

    private String content;

    private LocalDateTime lastEditTime;

    private String bookImgUrl;

    private Set<String> tags;

    private Set<String> likes;
}
