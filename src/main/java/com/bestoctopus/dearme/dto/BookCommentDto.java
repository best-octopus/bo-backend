package com.bestoctopus.dearme.dto;


import com.bestoctopus.dearme.domain.BookComment;
import com.bestoctopus.dearme.domain.BookReview;
import com.bestoctopus.dearme.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BookCommentDto {
    private long id;

    private String text;

    private String userNickname;

    private LocalDateTime lastEditTime;

    public static BookCommentDto fromEntity(BookComment bookComment){
        return BookCommentDto.builder()
                .id(bookComment.getId())
                .text(bookComment.getText())
                .userNickname(bookComment.getUser().getNickname())
                .lastEditTime(bookComment.getDate())
                .build();
    }

    public BookComment toEntity(User user,BookReview bookReview){
        return BookComment.builder()
                .text(this.text)
                .bookReview(bookReview)
                .user(user)
                .build();
    }
}
