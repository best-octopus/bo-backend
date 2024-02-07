package com.bestoctopus.dearme.dto;

import com.bestoctopus.dearme.domain.BookData;
import com.bestoctopus.dearme.domain.BookReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BookReviewListDto {
    private Long id;

    private String writer;

    private String title;

    private String content;

    private LocalDateTime lastEditTime;

    private String bookImgUrl;

    private String bookTitle;

    private Set<String> tags;

    private int likeNum;

    private int commentNum;

    public static BookReviewListDto fromEntity(BookReview bookReview){
        BookData bookData = bookReview.getBookData();
        return BookReviewListDto.builder()
                .id(bookReview.getId())
                .writer(bookReview.getUser().getNickname())
                .title(bookReview.getTitle())
                .content(bookReview.getContent())
                .lastEditTime(bookReview.getDate())
                .bookImgUrl(bookData.getImgUrl())
                .bookTitle(bookData.getTitle())
                .tags(bookReview.getTagNameSet())
                .likeNum(bookReview.getLikes().size())
                .commentNum(bookReview.getComments().size())
                .build();
    }
}
