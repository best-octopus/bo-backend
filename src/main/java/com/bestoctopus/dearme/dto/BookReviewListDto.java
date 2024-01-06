package com.bestoctopus.dearme.dto;

import com.bestoctopus.dearme.domain.BookReview;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class BookReviewListDto {

    private final Long id;

    private final String title;

    private final String content;

    private final String nickname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDate lastEditTime;


    public BookReviewListDto(BookReview bookreview) {
        this.id = bookreview.getId();
        this.title = bookreview.getTitle();
        this.content = bookreview.getContent();
        this.lastEditTime = bookreview.getLastEditTime();

        this.nickname = bookreview.getUser().getNickname();
    }
}
