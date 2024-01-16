package com.bestoctopus.dearme.dto;

import com.bestoctopus.dearme.domain.BookComment;
import com.bestoctopus.dearme.domain.BookReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class BookReviewResponseDto {

    private Long id;

    private String writer;

    private String title;

    private String content;

    private LocalDateTime lastEditTime;

    private BookDataDto bookData;

    private Set<String> tags;

    private Set<String> likes;

    private List<BookCommentDto> comments;

    public static BookReviewResponseDto fromEntity(BookReview bookReview){
        return BookReviewResponseDto.builder()
                .id(bookReview.getId())
                .writer(bookReview.getUser().getNickname())
                .title(bookReview.getTitle())
                .content(bookReview.getContent())
                .lastEditTime(bookReview.getDate())
                .bookData(BookDataDto.fromEntity(bookReview.getBookData()))
                .tags(bookReview.getTags().stream()
                        .map(tagRel->tagRel.getTag().getName()).collect(Collectors.toSet()))
                .likes(bookReview.getLikes().stream()
                        .map(like -> like.getUser().getNickname()).collect(Collectors.toSet()))
                .comments(bookReview.getComments().stream()
                        .map(BookCommentDto::fromEntity).toList())
                .build();
    }
}
