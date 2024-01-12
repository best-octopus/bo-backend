package com.bestoctopus.dearme.dto;



import com.bestoctopus.dearme.domain.BookReview;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BookReviewDto {
    private final Long id;

    private String reviewTitle;

    private String contents;

    private final String nickname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDate lastEditTime;


    public BookReviewDto(BookReview bookreview) {
        this.id = bookreview.getId();
        this.reviewTitle = bookreview.getTitle();
        this.contents = bookreview.getContent();
        this.lastEditTime = bookreview.getLastEditTime();

        this.nickname = bookreview.getUser().getNickname();
    }
    
}
